package edu.allegheny.twitteranalysis.io;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import edu.allegheny.twitteranalysis.Tweet;

import java.util.zip.DataFormatException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Dummy class for absent TwitterIO
 * @author ArcticLight
 */
public class TwitterIO {
    /**
     * Takes a {@link File}, a Twitter archive zip file, and returns, as a list, all of the
     * {@link Tweet}s contained in the zip.
     * @throws ParseException 
     * @throws DataFormatException if the given file is not a zip file or is not a twitter archive
     */
    public static List<Tweet> parseZip(File zipfile) throws ZipException, IOException, ParseException {
    	ZipFile zip = null;
    	try {
            zip = new ZipFile(zipfile);
            ZipEntry csv = zip.getEntry("tweets.csv");
            if(csv == null) throw new IOException("tweets.csv not found in zip \"" + zipfile.getPath().toString() + "\"");
            return parseCSV(new InputStreamReader(zip.getInputStream(csv)));
        } finally {
            if(zip != null) zip.close();
        }
    }
    
    public static List<Tweet> parseCSV(Reader reader) throws IOException, ParseException {
    	CSVReader read = new CSVReader(reader);
    	ArrayList<Tweet> list = new ArrayList<Tweet>();
    	List<String[]> lines = read.readAll();
    	DateFormat dt = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss z");
    	dt.setTimeZone(TimeZone.getTimeZone("GMT"));
    	lines.remove(0);
    	for(String[] srs : lines) {
    		Date date;
    		date = dt.parse(srs[3]);
    		Calendar GMT = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    		GMT.setTime(date);
    		GMT.add(Calendar.MILLISECOND, TimeZone.getDefault().getOffset(date.getTime()));
    		GMT.add(Calendar.HOUR, 1);
    		list.add(new Tweet(srs[5], GMT.getTime(), srs[0], srs[4], srs[2], srs[6], srs[7]));
    	}
    	read.close();
    	return list;
    }
}
