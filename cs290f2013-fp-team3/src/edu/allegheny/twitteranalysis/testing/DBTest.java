package edu.allegheny.twitteranalysis.testing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.allegheny.twitteranalysis.Tweet;
import edu.allegheny.twitteranalysis.db.ConnectionFailedException;
import edu.allegheny.twitteranalysis.db.DesktopDB;
import static org.junit.Assert.*;

public class DBTest {
	DesktopDB database;
	
	/**
	* Before beginning tests, connect to the database and clear it
	*/
	@Before public void initialize() throws ConnectionFailedException {
		database = new DesktopDB();
		database.init();
		database.clearDB();
	}
	
	/**
	* After the tests are complete, close the database
	*/
	@After public void endTest() throws IllegalStateException {
		database.close();
	}
	
	/**
	* Test the initialization of the database by checking that the database has is successfuly emptied
	*/
	@Test
	public void testNewDB() {
		try {
			assertEquals(database.getAllTweets().size(), 0);
		} catch(Exception e) {
			fail();
		}
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.db.DesktopDB#insertTweets(Tweet...) insertTweets} with a single tweet by inserting it into the database, retrieving it, and comparing the output to the input.
	 */
	@Test
	public void testInsertOne() {
		Tweet t = new Tweet("TEST", new Date(), "0", "");
		database.insertTweets(t);
		List<Tweet> q = database.getAllTweets();
		assertEquals(q.get(0), t);
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.db.DesktopDB#insertTweets(Tweet...) insertTweets} with an arbitrarily generated large list of tweets by inserting them into the database, retrieving them, and comparing the output to the input.
	 */
	@Test
	public void testInsertMany() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(100);
		for(int i = 0; i < 100; i++) {
			test.add(new Tweet("Test " + i, new Date(), "" + i, ""));
		}
		
		database.insertTweets((Tweet[])test.toArray(new Tweet[1]));
		List<Tweet> resultSet = database.getAllTweets();
		for(Tweet t : test) {
			if(!resultSet.contains(t)) fail();
		}
		for(Tweet t : resultSet) {
			if(!test.contains(t)) fail();
		}
	}

	/**
	 * Test inserting two identical {@link Tweet}s by verifying that only one is inserted
	 */
	@Test
	public void testInsertNonUnique() {
		Tweet[] test = new Tweet[2];
		test[0] = new Tweet("12345", new Date(), "0", "");
		test[1] = new Tweet("12345", new Date(), "0", "");
		database.insertTweets(test);
		ArrayList<Tweet> all = (ArrayList<Tweet>) database.getAllTweets();
		assertEquals(all.size(), 1);
	}
	
	/**
	 * Test invalid characters in a Tweet's text body
	 */
	@Test
	public void testInvalidChar() {
	    Tweet test = new Tweet("\" Invalid ; --", new Date(), "", "");
	    database.insertTweets(test);
	    try {
	    	assertEquals(database.getAllTweets().get(0), test);
	    } catch(Exception e) {
	    	fail();
	    }
    }
	
	/**
	* Test the {@link edu.allegheny.twitteranalysis.db.DesktopDB#close() close} method by excersizing the method, then attempting to execute another function on the database.  Since the connection should be closed, we expect an IllegalStateException to be thrown.
	*/
	@Test(expected=IllegalStateException.class)
	public void testClose() throws IllegalStateException {
		database.close();
		database.clearDB();
	}
	
	/** 
	* Test the error capture in {@link edu.allegheny.twitteranalysis.db.DesktopDB#getAllTweets() getAllTweets} by closing the database and then attempting to make use of the method.  The expected behavior in this case is the throwing of an IllegalStateException
	*/
	@Test(expected=IllegalStateException.class)
	public void testGetAfterClose() throws IllegalStateException {
		database.close();
		database.getAllTweets();
	}
}
