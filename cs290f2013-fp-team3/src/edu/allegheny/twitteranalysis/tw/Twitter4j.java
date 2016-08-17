package edu.allegheny.twitteranalysis.tw;


import twitter4j.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import edu.allegheny.twitteranalysis.Tweet;

public class Twitter4j{

    /**
     * Returns the screen name assosciated with the id provided, given a {@link Twitter} object
     *
     * @param id The string representation of the id to be converted to its assosciated screen name
     * @param twitter A Twitter object that allows access to Twitter's servers
     * @return A string containing the screen name associated with the provided id
     */
	public static String getUser(String id, Twitter twitter) {
		long l;
		User u = null;
		try {
			l = Long.parseLong(id);
			u = twitter.showUser(l);
		} catch(TwitterException e) {
			return id;
		} catch(NumberFormatException e) {
		    return id;
		}
		return u.getScreenName();
	}

    /**
     * Retrieves the most recent 20 tweets from the Twitter account of the user assosciated with the 
     * provided {@link Twitter} object
     *
     * @param twitter A Twitter object that allows access to Twitter's servers
     * @return A list of the most recent 20 tweets from the provided Twitter object
     */
	public static List<Tweet> getTimeline(Twitter twitter){
		ResponseList<Status> u = null;
		ArrayList<Tweet> ret = new ArrayList<Tweet>();
		try{
			u = twitter.getUserTimeline();
			for(int i = 0; i < u.size(); i++){
			    Status temp = u.get(i);
			    String text = temp.getText();
			    String id = "" + temp.getId();
			    String source = temp.getSource();
                Date timestamp = temp.getCreatedAt();
                String reply_to_id = "" + temp.getInReplyToStatusId();
                String retweeted_status_id;
                String retweeted_status_user_id;
                if(temp.isRetweet()) {
                    Status retweet = temp.getRetweetedStatus();
                    retweeted_status_id = "" + retweet.getId();
                    retweeted_status_user_id = "" + retweet.getUser().getId();
                }
                else {
                    retweeted_status_id = "";
                    retweeted_status_user_id = "";
                }
                Tweet t = new Tweet(text, timestamp, id, source, reply_to_id, retweeted_status_id, 
                        retweeted_status_user_id);
                ret.add(t);
			}
		} catch(TwitterException e) {
			return ret;
		} catch(NumberFormatException e) {
            return ret;
        }
		return ret;
    }
}
