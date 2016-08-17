package edu.allegheny.twitteranalysis.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.zip.ZipException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.TwitterException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import au.com.bytecode.opencsv.*;
import edu.allegheny.twitteranalysis.Tweet;
import edu.allegheny.twitteranalysis.db.ConnectionFailedException;
import edu.allegheny.twitteranalysis.db.DBConnection;
import edu.allegheny.twitteranalysis.db.DesktopDB;
import edu.allegheny.twitteranalysis.io.TwitterIO;
import edu.allegheny.twitteranalysis.oa.OAuthAccess;
import edu.allegheny.twitteranalysis.oa.TwitterAuthenticator;
import edu.allegheny.twitteranalysis.ta.TwitterAnalytics;
import edu.allegheny.twitteranalysis.tw.Twitter4j;

/**
 * The main interface for the program.
 */
public class Main {	
	public static class JCommanderParms {
		@Parameter(names={"-zip"})
		public String zipfile;
		
		@Parameter(names={"-oa"})
		public String oafile;
		
		@Parameter(names={"--clear", "-c"})
		public boolean cleardb;
		
		@Parameter(names={"-auth"})
		public String authfile;
		
		@Parameter(names={"--verbose", "-v"})
		public boolean verbose;
		
		@Parameter(names={"--help", "-h", "-?"})
		public boolean helpme;
		
		@Parameter
		public List<String> analysis;
	}
	
	public static final String USAGESTR =
		"Usage:\n" +
		"java -jar burdwatch.jar [OPTION...] [ANALYZER]\n" +
		"Performs analysis on the available tweet data using the\n" +
		"specified analyzer.\n" +
		"\n" +
		"ANALYZERS:\n" +
		"date                            Identifies the date on which the\n" +
		"                                most tweets occurred\n" +
		"\n" +
		"pattern  PATTERN                Prints all tweets that contain the\n" +
		"                                given pattern\n" +
		"\n" +
		"ratio                           Prints the ratio of retweets to\n" +
		"                                non-retweet tweets\n" +
		"\n" +
		"device                          Identifies and prints the device\n" +
		"                                most used to post tweets\n" +
		"\n" +
		"OPTIONS:\n" +
		"-auth FILE                      Connect to the Internet by\n" +
		"                                performing Twitter authentication,\n" +
		"                                and write token result to the\n" +
		"                                specified file for later.\n" +
		"\n" +
		"-oa FILE                        Connect to the Internet using the\n" +
		"                                OAuth file created previously by\n" +
		"                                the -auth option.\n" +
		"\n" +
		"-zip FILE                       Adds the contents of the specified\n" +
		"                                Twitter archive file to the\n" +
		"                                internal database.\n" +
		"\n" +
		"--verbose                       Verbose mode, which prints out\n" +
		"     -v                         technical error messages.\n" +
		"\n" +
		"--clear                         Clears the database first before\n" +
		"     -c                         doing anything else.\n";
	
    public static void main(String[] args) {
    	JCommanderParms parameters = new JCommanderParms();
    	new JCommander(parameters, args);
    	boolean offline = false;
    	java.util.Scanner in = new java.util.Scanner(System.in);
    	
    	//prepare other modules' stuff here
    	ErrorHandler err = new ErrorHandler(parameters.verbose);
    	TwitterAuthenticator oa = new OAuthAccess();
    	DBConnection db = new DesktopDB();
    	AccessToken token = null;
    	
    	//handle immediate error conditions we can determine from the commandline
    	//case 1: No analysis type, and no auth parameter
    	if(parameters.helpme || ((parameters.analysis == null || parameters.analysis.size() == 0) &&
    			(parameters.oafile == null || parameters.authfile == null ||
    			(parameters.oafile.equals("") || parameters.authfile.equals(""))))) {
    		System.err.println("Error: No analysis type selected.");
    		System.err.println(USAGESTR);
    		err.equit(null);
    	//case 2: Too many options that weren't an analysis or recognized flag
    	} else if(parameters.analysis.size() > 2) {
    		System.err.println("Error: Malformed command line options. Don't know what");
    		System.err.println("       to do with option pattern:");
    		System.err.print("       [");
    		for(String s : parameters.analysis) {
    			System.err.print("\"" + s + "\"");
    		}
    		System.err.println("]");
    		err.equit(null);
    	}
    	//case 3: Given two options, check to make sure it's a valid pair. Otherwise, error.
    	else if(parameters.analysis.size() == 2) {
    		String p1 = parameters.analysis.get(0);
    		String p2 = parameters.analysis.get(1);
    		
    		//the only option that takes a parameter also is the pattern option at the moment.
    		//written this way so that we can add more later, maybe
    		if(!p1.toLowerCase().equals("pattern")) {
    			System.err.println("Error: Unknown command \"" + p1 + " " + p2 + "\"");
    			err.equit(null);
    		}
    	}
    	//case 4: Given one option, check to make sure it's valid. Otherwise, error
    	else {
    		
    		//this is a terrible way to do this but it gets the job done and we can add more recognized
    		//options fairly easily.
    		switch(parameters.analysis.get(0).toLowerCase()) {
    			case "date":
    			case "most-retweeted":
    			case "ratio":
    			case "device":
    				break;
    			default:
    				System.err.println("Error: Unknown command \"" + parameters.analysis.get(0) + "\"");
    				System.exit(1);
    		}
    	}
    	
    	//handle weird user input cases.
    	if(parameters.analysis == null) {
    		System.out.println("Warning: No analysis selected.");
    	}
    	
    	System.out.println("Signing in with Twitter...");
    	Twitter signin = TwitterFactory.getSingleton();
    	
    	String consumerKey = null, consumerSecret = null;
    	CSVReader csvIn = null;
    	try {
    		csvIn = new CSVReader(new FileReader(new File("keys.csv")));
    		List<String[]> csv = csvIn.readAll();
    		csvIn.close();
    		consumerKey = csv.get(0)[0];
    		consumerSecret = csv.get(0)[1];
    	} catch(FileNotFoundException e) {
    		System.err.println("Error: Unable to find keys.csv, please ensure this file is in your");
    		System.err.println("       working directory when you run Burdwatch.");
    		err.equit(e);
    	} catch (IOException e) {
    		System.err.println("Error: Unable to read keys.csv. Please ensure it is in the correct");
    		System.err.println("       format. Check the documentation.");
    		err.equit(e);
		} catch (Exception e) {
			System.err.println("Error: An unexpected error occurred while reading in the application");
			System.err.println("       Twitter consumer and secret keys.");
			err.equit(e);
		}
    	
    	signin.setOAuthConsumer(consumerKey, consumerSecret);
    	//begin working out whether we are online or offline, and whether authentication is needed.
    	if(parameters.oafile == null && parameters.authfile == null) {
    		System.out.println("Warning: No Twitter authentication method given.");
    		System.out.println("         (Provide one with the -oa or -auth flags in the future)");
    		offline = true;
    	} else if (parameters.authfile != null && !parameters.authfile.equals("")) {
    		try {
    		    token = performTextSignin(oa, in, consumerKey, consumerSecret, err);
    		    signin.setOAuthAccessToken(token);
				writeOAFile(token, parameters.authfile);
				offline = false;
			} catch (TwitterException e) {
			    System.err.println("Error: Twitter error while signing in.");
                err.maybeprint(e);
                offline = true;
			} catch (IOException e) {
				System.err.println("Error: Unable to write the file \"" + parameters.authfile + "\".");
				System.err.println("       You will continue to be signed in for this session,");
				System.err.println("       however you won't be able to reload with this file.");
			} catch (Exception e) {
			    err.equit(e);
            }
    	} else if (parameters.oafile != null && !parameters.oafile.equals("")){
    		try {
    			token = readOAFile(parameters.oafile);
    			signin.setOAuthAccessToken(token);
    			offline = false;
    		} catch(FileNotFoundException e) {
    			System.err.println("Error: Cannot find file \"" + parameters.oafile + "\"");
    			offline = true;
    		} catch (Exception e) {
    			System.err.println("Error: Cannot read file \"" + parameters.oafile + "\"");
    			offline = true;
            }
    	} else {
    		System.err.println("Error: Authentication given, but there was no filename.");
    		System.err.println("       You need to provide a filename to use the auth options.");
    		offline = true;
    	}
    	
    	//tell the user if we've
    	//remained offline through all of this.
    	if(offline) {
    		System.out.println("Warning: Sign-in failure. Online functions requiring a Twitter");
    		System.out.println("         connection or an Internet connection are unavailable.");
    	} else {
    		
    	}
    	
    	System.out.println("Setting up the database connection...");
    	try {
			db.init();
		} catch (ConnectionFailedException e) {
			System.err.println("Error: Unable to connect to the internal database.");
			System.err.println("       System cannot continue.");
			err.equit(e);
		}
    	
    	//handle dbclear
    	if(parameters.cleardb) { 
    		System.out.println("Clearing the database...");
    		try {
    			db.clearDB();
    		} catch(IllegalStateException e) {
    			err.ewarn("Couldn't clear the database! Some old messages may remain.", e);
    		}
    	}
    	
    	//handle zip parameter
    	if(parameters.zipfile != null) {
    		if(!parameters.zipfile.equals("")) {
    			System.out.println("Reading Twitter archive \"" + parameters.zipfile + "\"...");
    			try {
					db.insertTweets(TwitterIO.parseZip(new File(parameters.zipfile)).toArray(new Tweet[0]));
				} catch (ZipException e) {
					System.err.println("Error: Zip could not be parsed as a Twitter zip archive.");
					System.err.println("       Skipping this task.");
					err.maybeprint(e);}
    			catch (ParseException e) {
    				System.err.println("Error: Error parsing the tweets.csv file.");
    				System.err.println("       Skipping this task.");
					err.maybeprint(e);
				} catch (IOException e) {
					System.err.println("Error: Something bad happened while reading the tweets.csv");
					System.err.println("       file inside the archive. Skipping this task.");
					err.maybeprint(e);
				} catch (IllegalStateException e) {
					System.err.println("Error: Unable to interact with the database.");
					System.err.println("       This is a fatal error, and the system cannot continue.");
					err.equit(e);
				}
    			
    		} else {
    			System.err.println("Error: Zip file parameter given, but there was no filename.");
    			System.err.println("       You must specify a zip file for loading;");
    			System.err.println("       Skipping this task.");
    		}
    	}
    	
    	//Handle Twitter refresh...
    	if(!offline) {
    		System.out.println("Refreshing database with your most recent tweets...");
    		List<Tweet> ref = Twitter4j.getTimeline(signin);
    		try {
    			db.insertTweets(ref.toArray(new Tweet[0]));
    		} catch (IllegalStateException e) {
    			System.err.println("Error: Unable to interact with the database.");
    			System.err.println("       This is a fatal error, and the system cannot continue.");
    			err.equit(e);
    		}
    	}
    	
    	//Do analysis
    	String response;
    	switch(parameters.analysis.get(0).toLowerCase()) {
    		case "date":
    			System.out.println("Running day analysis on stored tweets...");
    			response = TwitterAnalytics.mostTweetedDay(db.getAllTweets());
    			System.out.printf("Your most tweeted day: %s%n", response);
    			return;
    		case "most-retweeted":
    			System.out.println("Running most-retweeted analysis on stored tweets...");
    			response = TwitterAnalytics.mostRetweeted(db.getAllTweets());
    			if(!offline) {
    				response = Twitter4j.getUser(response, signin);
    			}
    			System.out.printf("You have most retweeted the user: %s%n", response);
    			if(offline) {
    				System.out.println("(Sign in with Twitter to display the username instead");
    				System.out.println("of the user's ID number)");
    			}
    			return;
    		case "ratio":
    			System.out.println("Running ratio analysis on stored tweets...");
    			double ratio = TwitterAnalytics.retweetRatio(db.getAllTweets());
    			System.out.printf("Your ratio of retweets to tweets is: %01.3f%n", ratio);
    			return;
    		case "device":
    			System.out.println("Running device analysis on stored tweets...");
    			String device = TwitterAnalytics.mostUsedDevice(db.getAllTweets());
    			System.out.printf("The device you used most is: %s%n", device);
    			return;
    		case "pattern":
    			String pattern = parameters.analysis.get(1);
    			System.out.println("Running pattern analysis on stored tweets...");
    			System.out.printf("Using pattern \"%s\"...%n", pattern);
    			List<Tweet> tweets = TwitterAnalytics.matchPattern(db.getAllTweets(), pattern);
    			System.out.println("Stored tweets that match the given pattern:");
    			for(Tweet t : tweets) {
    				System.out.println(t.getText());
    			}
    			return;
    		default:
    			err.equit(null);
    	}
    	
    }

	private static void writeOAFile(AccessToken token, String authfile) throws IOException {
		File f = new File(authfile);
		FileOutputStream fstream = null;
		ObjectOutputStream out = null;
		try {
			fstream = new FileOutputStream(f, false);
			out = new ObjectOutputStream(fstream);
			out.writeObject(token);
			out.close();
			fstream.close();
		} catch (Exception e1) {
			throw new IOException(e1);
		}
	}
	
	private static AccessToken readOAFile(String authfile) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(authfile);
		FileInputStream fstream = null;
		ObjectInputStream in = null;
		try {
			fstream = new FileInputStream(f);
			in = new ObjectInputStream(fstream);
			AccessToken r = (AccessToken)in.readObject();
			in.close();
			fstream.close();
			return r;
		}  catch (IOException e) {
			if(in != null) in.close();
			if(fstream != null) fstream.close();
			throw e;
		} catch (ClassNotFoundException e) {
			in.close();
			fstream.close();
			throw e;
		}
	}

	private static AccessToken performTextSignin(TwitterAuthenticator oa, java.util.Scanner in, String consumerKey, String consumerSecret, ErrorHandler err) throws TwitterException {
		System.out.println("Performing Twitter sign-in.");
		System.out.println("If your browser does not automatically open, please");
		System.out.println("paste the following URL from twitter into it and enter");
		System.out.print("your pin number: ");
		String URL = oa.getAuthURL(consumerKey, consumerSecret);
	    System.out.println(URL);
		System.out.print("\nYour PIN: ");
		if(java.awt.Desktop.isDesktopSupported()) {
			try {
				java.awt.Desktop.getDesktop().browse(new URI(URL));
			} catch (URISyntaxException e) {
				err.maybeprint(e);
			} catch (IOException e) {
				err.maybeprint(e);
			}
		}
		String pin = in.nextLine();
		return oa.setPIN(pin);
	}
}
