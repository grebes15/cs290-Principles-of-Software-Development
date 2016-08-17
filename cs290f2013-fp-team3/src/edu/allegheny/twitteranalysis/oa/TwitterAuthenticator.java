package edu.allegheny.twitteranalysis.oa;

import twitter4j.auth.AccessToken;
import twitter4j.TwitterException;

public interface TwitterAuthenticator {
	
	/**
	 * Provides a String URL that to redirects the user to the Twitter website, where they can log in
	 * and obtain a PIN 
	 * 
	 * @param consumerKey String of the developer key
	 * @param consumerSecret String of the developer secret key
	 * @return The URL for the user to enter
	 * @throws TwitterException If there was a problem obtaining the URL from Twitter
	 */
    public String getAuthURL(String consumerKey, String consumerSecret) throws TwitterException;
    
    /**
     * Provides a Twitter AccessToken for authentication a user with Twitter
     * 
     * @param PIN A PIN number obtained from Twitter for OAuth authentication 
     * @return Twitter AccessToken for authentication
     * @throws TwitterException If there was a problem obtaining the AccessToken
     */
    public AccessToken setPIN(String PIN) throws TwitterException;
}
