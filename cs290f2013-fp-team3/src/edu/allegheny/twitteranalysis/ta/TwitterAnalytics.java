package edu.allegheny.twitteranalysis.ta;
import edu.allegheny.twitteranalysis.Tweet;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TwitterAnalytics {
    
    /**
     * Returns all {@link Tweet}s from a list of Tweets that match a given pattern
     * If the pattern is given as the empty string, returns a list of all tweets
     * 
     * @param T A {@link List} of tweets
     * @param pattern A string representing the pattern to consider
     * @return A list (size >= 0) representing the set of tweets whose text matches the given pattern
     */
    public static List<Tweet> matchPattern(List<Tweet> T, String pattern) {
        ArrayList<Tweet> ret = new ArrayList<Tweet>(T.size());
        for(Tweet t : T) {
            if(t.getText().contains(pattern)) {
                ret.add(t);
            }
        }
        return ret;
    }
    
    /**
     * Returns the day of the week on which tweeting occurred the most, within the provided list
     *
     * @param T A {@link List} of {@link Tweet}s 
     * @return The empty string if there are no tweets, or the day of the week as a string,
     * formatted to the default locale
     */
    public static String mostTweetedDay(List<Tweet> T) {
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        for(Tweet t : T) {
            Date timestamp = t.getTimestamp();
            String day = (new java.text.SimpleDateFormat("EEEE")).format(timestamp);
            if(freq.containsKey(day)) {
                freq.put(day, freq.get(day) + 1);
            } else {
                freq.put(day, 1);
            }
        }

        String maxd = "";
        int max = 0;
        for(String day : freq.keySet()) {
            if(freq.get(day) > max) {
                max = freq.get(day);
                maxd = day;
            }
        }

        return maxd;
    }
    
    /**
     * Returns the most common retweeted_status_user_id from the list of tweets provided
     *
     * @param T A {@link List} of {@link Tweet}s 
     * @return The empty string if there were no tweets, or the most common retweeted_status_user_id.
     * In the case that there is a tie, behavior is undefined where one of the tied items will be 
     * returned in no particular fashion.  The empty string is not counted as a retweeted id
     */
    public static String mostRetweeted(List<Tweet> T) {
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        for(Tweet t : T) {
             String id = t.getRetweeted_status_user_id();
             if(id.equals("")) continue;
             if(freq.containsKey(id)) {
                 freq.put(id, freq.get(id) + 1);
             } else {
                 freq.put(id, 1);
             }
        }

        String maxid = "";
        int max = 0;
        for(String id : freq.keySet()) {
            if(freq.get(id) > max) {
                max = freq.get(id);
                maxid = id;
            }
        }
        return maxid;
    }
    /**
     * Takes a {@link List} of {@link Tweet}s and returns the percentage of tweets
     * that are retweets, as a double.  If there are no tweets in the list, returns 0.0
     * 
     * @param T A {@link List} of {@link Tweet}s
     * @return The percentage of tweets that are retweets, represented as a double
     */
    public static double retweetRatio(List<Tweet> T) {
    	int retweets = 0;
    	for(Tweet t : T) {
    		String id = t.getRetweeted_status_user_id();
    		if(id == null || !id.equals("")) retweets++;
    	}
    	return (T.size() == 0)? 0 : (float)(retweets) / T.size();
    }
    
    /**
     * Returns the most common source from the list of tweets provided
     * 
     * @param T A {@link List} of {@link Tweet}s
     * @return The empty string if there were no tweets, or the most common source.
     * In the case that there is a tie, behavior is undefined where one of the tied items will be 
     * returned in no particular fashion.
     */
    public static String mostUsedDevice(List<Tweet> T) {
    	HashMap<String, Integer> freq = new HashMap<String, Integer>();
    	for(Tweet t : T) {
            String source = t.getSource();
            if(freq.containsKey(source)) {
                freq.put(source, freq.get(source) + 1);
            } else {
                freq.put(source, 1);
            }
    	}
    	
    	String maxsource = "";
        int max = 0;
        for(String source : freq.keySet()) {
            if(freq.get(source) > max) {
                max = freq.get(source);
                maxsource = source;
            }
        }
        return maxsource;
    }
}
