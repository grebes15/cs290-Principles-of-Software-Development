package edu.allegheny.twitteranalysis.oa;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;
import twitter4j.TwitterException;

public class OAuthAccess implements TwitterAuthenticator {

    //Builder is able to construct a twitter4j config with the settings the user wants
    //ConfigurationBuilder cb = new ConfigurationBuilder();
    Configuration config = null;
    RequestToken requestToken = null;
    

    @Override
    public String getAuthURL(String consumerkey, String consumersecret) throws TwitterException
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerkey);
        cb.setOAuthConsumerSecret(consumersecret);
        this.config = cb.build();
        TwitterFactory tf = new TwitterFactory(this.config);
        Twitter twitter = tf.getInstance();
        requestToken = null;
        requestToken = twitter.getOAuthRequestToken();
        return requestToken.getAuthorizationURL();
    }

    @Override
    public AccessToken setPIN(String PIN) throws TwitterException
    {
        if(config == null || requestToken == null) throw new TwitterException("setPIN called before getAuthURL success!");
        TwitterFactory tf = new TwitterFactory(this.config);
        Twitter twitter = tf.getInstance();
        AccessToken accessToken = null;
        
        try {
            //inputs the users inputted pin and the request token
            if (PIN.length() > 0) {
                accessToken = twitter.getOAuthAccessToken(requestToken, PIN);
            } else { 
                //simply gets the requesttoken and puts it into the 
                //getauthorizationtoken method
                accessToken = twitter.getOAuthAccessToken(requestToken);
            } 
        } catch (TwitterException te) {
            if (401 == te.getStatusCode()) {
                throw new TwitterException("Unable to get the access token", te);
            } else {
                throw te;
            }		
        }
        return accessToken;
    }
}
