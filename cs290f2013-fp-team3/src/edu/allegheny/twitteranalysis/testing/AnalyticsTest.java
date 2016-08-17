package edu.allegheny.twitteranalysis.testing;
   
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.allegheny.twitteranalysis.Tweet;
import edu.allegheny.twitteranalysis.ta.TwitterAnalytics;
import static org.junit.Assert.*;

public class AnalyticsTest {
	
	/**
	* A long value representing Wed, Dec 4, 2013 in milliseconds
	*/
	static final long W_DEC_4_2013 = 1386134654295L;
	
	/**
	* A long value representing Tue, Dec 3, 2013 in milliseconds
	*/
	static final long TU_DEC_3_2013 = 1386134654295L - 30*60*60*1000;
	
	/**
	* A long value representing an arbitrary Friday in milliseconds
	*/
	static final long SOME_FRIDAY = 133234234L;

	/**
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#matchPattern(List, String) matchPattern} on an arbitrary list of one {@link Tweet} and the empty string as a pattern
	*/
	@Test
	public void testEmptyMatchPattern() {
		Tweet t = new Tweet("Test", new Date(), "0", "");
		ArrayList<Tweet> list = new ArrayList<Tweet>(1);
		list.add(t);
		assertEquals(TwitterAnalytics.matchPattern(list, "").size(), list.size());
	}

	/**
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#matchPattern(List, String) matchPattern} on an arbitrary list of one {@link Tweet} and an arbitrary pattern that is not contained in the message of the tweet
	*/
	@Test
	public void testFindNoneMatchPattern() {
		Tweet t = new Tweet("Test", new Date(), "0", "");
		ArrayList<Tweet> list = new ArrayList<Tweet>(1);
		list.add(t);
		assertEquals(TwitterAnalytics.matchPattern(list, "x").size(), 0);
	}
	
	/**
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#matchPattern(List, String) matchPattern} on an arbitrary list of one {@link Tweet} and an arbitrary pattern that is contained in the message of the tweet
	*/
	@Test
	public void testFindOneMatchPattern() {
		Tweet t = new Tweet("Test", new Date(), "0", "");
		ArrayList<Tweet> list = new ArrayList<Tweet>(1);
		list.add(t);
		assertEquals(TwitterAnalytics.matchPattern(list, "e").size(), 1);
	}
	
	/** 
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#matchPattern(List, String) matchPattern} on an arbitrary list of 100 {@link Tweet}s and a pattern that is present in some of the messages but not all
	*/
	@Test
	public void testFindManyMatchPattern() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(100);
		for(int i = 0; i < 100; i++) {
			test.add(new Tweet("Test " + i, new Date(), "" + i, ""));
		}
		assertEquals(TwitterAnalytics.matchPattern(test, "1").size(), 19);
	}
	
	/**
	* Test m{@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostTweetedDay(List) mostTweetedDay} on an empty list
	*/
	@Test
	public void testNoMostTweetedDay() {
		assertEquals(TwitterAnalytics.mostTweetedDay(new ArrayList<Tweet>()), "");
	}
	
	/**
	* Test  {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostTweetedDay(List) mostTweetedDay} on an arbitrary list containing a majority of {@link Tweet}s tweeted on one day and a minority tweeted on another day
	*/
	@Test
	public void testManyMostTweetedDay() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(100);
		for(int i = 0; i < 99; i++) {
			test.add(new Tweet("Test " + i, new Date(SOME_FRIDAY), "" + i, ""));
		}
		test.add(new Tweet("Test 99", new Date(W_DEC_4_2013), "99", ""));
		
		assertEquals(TwitterAnalytics.mostTweetedDay(test), "Friday");
	}
	
	/**
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostRetweeted(List) mostRetweeted} on an arbitrary list of one {@link Tweet} that is not a retweet
	*/
	@Test
	public void testNoMostRetweeted() {
		Tweet t = new Tweet("Test", new Date(), "0", "");
		ArrayList<Tweet> test = new ArrayList<Tweet>(1);
		test.add(t);
		assertEquals(TwitterAnalytics.mostRetweeted(test), "");
	}
	
	/**
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostRetweeted(List) mostRetweeted} on an arbitrary list of one {@link Tweet} that is a retweet
	*/
	@Test
	public void testOneMostRetweeted() {
		Tweet t = new Tweet("Test", new Date(), "0", "", "", "0", "47");
		ArrayList<Tweet> test = new ArrayList<Tweet>(1);
		test.add(t);
		assertEquals(TwitterAnalytics.mostRetweeted(test), "47");
	}
	
	/**
	* Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostRetweeted(List) mostRetweeted} on an arbitrary list of 100 {@link Tweet}s, all of which are retweets, where the majority were originally tweeted by one user and the remainder were tweeted by another user
	*/
	@Test
	public void testManyMostRetweeted() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(100);
		for(int i = 0; i < 55; i++) {
			test.add(new Tweet("Test", new Date(), "0", "", "", "0", "47"));
		}
		for(int i = 56; i < 100; i++) {
			test.add(new Tweet("Test", new Date(), "0", "", "", "0", "23"));
		}
		assertEquals(TwitterAnalytics.mostRetweeted(test), "47");
	}
	
	/**
	* Show that the default constructor exists
	*/
	@Test
	public void testConstructor() {
		assertNotNull(new TwitterAnalytics());
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostUsedDevice(List) mostUsedDevice} by
	 * executing the method on an arbitrary list of one {@link Tweet} and comparing the input and output
	 */
	@Test
	public void testOneMostUsedDevice() {
		Tweet t = new Tweet("Test", new Date(), "0", "mars");
		ArrayList<Tweet> test = new ArrayList<Tweet>(1);
		test.add(t);
		assertEquals(TwitterAnalytics.mostUsedDevice(test), t.getSource());
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#mostUsedDevice(List) mostUsedDevice} by
	 * executing the method on an arbitrary list of 100 {@link Tweet}s and comparing the input and output
	 */
	@Test
	public void testManyMostUsedDevice() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(100);
		for(int i = 0; i < 40; i++) {
			test.add(new Tweet("Test", new Date(), "0", "mars"));
		}
		
		for(int i = 0; i < 60; i++) {
			test.add(new Tweet("Test", new Date(), "0", "venus"));
		}
		assertEquals(TwitterAnalytics.mostUsedDevice(test), "venus");
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#retweetRatio(List) retweetRatio}
	 * by executing the method on an arbitrary list of one {@list Tweet}s and verifying the output
	 */
	@Test
	public void testOneRetweetRatio() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(1);
		test.add(new Tweet("0", new Date(), "", "", "", "123", "153"));
		assertEquals(TwitterAnalytics.retweetRatio(test), 1.0, 0.000001);
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#retweetRatio(List) retweetRatio}
	 * by executing the method on an arbitrary list of two {@list Tweet}s where one is a retweet
	 * and one is not a retweet
	 */
	@Test
	public void testTwoRetweetRatio() {
		ArrayList<Tweet> test = new ArrayList<Tweet>(2);
		test.add(new Tweet("0", new Date(), "", "", "", "123", "153"));
		test.add(new Tweet("0", new Date(), "", ""));
		assertEquals(TwitterAnalytics.retweetRatio(test), 0.5, 0.000001);
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#retweetRatio(List) retweetRatio}
	 * by executing the method on an empty list of {@list Tweet}s 
	 */
	@Test
	public void testNoRetweetRatio() {
		ArrayList<Tweet> test = new ArrayList<Tweet>();
		assertEquals(TwitterAnalytics.retweetRatio(test), 0, 0.000001);
	}
	
	/**
	 * Test {@link edu.allegheny.twitteranalysis.ta.TwitterAnalytics#retweetRatio(List) retweetRatio}
	 * by executing the method on an arbitrary list of two {@list Tweet}s where one is a retweet
	 * and one is not a retweet
	 */
	@Test
	public void testManyRetweetRatio() {
		ArrayList<Tweet> test = new ArrayList<Tweet>();
		for(int i = 0; i < 40; i++) {
			test.add(new Tweet("Test", new Date(), "0", "", "", "123", "42"));
		}
		
		for(int i = 0; i < 60; i++) {
			test.add(new Tweet("Test", new Date(), "0", "", "", "", ""));
		}
		assertEquals(TwitterAnalytics.retweetRatio(test), 0.4, 0.000001);
	}
}
