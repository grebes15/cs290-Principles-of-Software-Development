package edu.allegheny.twitteranalysis.db;

import java.util.List;
import edu.allegheny.twitteranalysis.Tweet;

/**
 * An abstract representation of a database connection.
 */
public abstract class DBConnection {
    /**
     * Initializes the connection to the private database.
     * If the database is unavailable or does not exist,
     * an attempt should be made to construct it.
     * @throws ConnectionFailedException in the case that the database does not
     * exist and could not be created.
     */
    public abstract void init() throws ConnectionFailedException;
    
    /**
     * Clears an existing database of all previous data. If the connection was not
     * previously initialized, throws an error. If the database is already clean,
     * does nothing.
     * @throws IllegalStateException if this database connection has not been initialized.
     */
    public abstract void clearDB() throws IllegalStateException;
    
    /**
     * Accepts {@link Tweet}s to insert to the database.
     * Method implementations should silently handle the following cases:
     * 1. If the list is empty, this method should become a NOP.
     * 2. If the list contains any tweets which are duplicates of an existing
     * tweet already in the database, they should be ignored.
     * @param T the Tweet(s) to add.
     * @throws IllegalStateException if init() has not been called yet.
     */
    public abstract void insertTweets(Tweet... T) throws IllegalStateException;

    /**
     * Closes the connection to the database and releases held resources.
     * It should be noted that the database is no longer initialized after this is run!
     * @throws IllegalStateException if the database is not initialized.
     */
    public abstract void close() throws IllegalStateException;
    
    /**
     * Returns a list of all the tweets in the database.  If there are no
     * tweets, return an empty list
     * @throws IllegalStateException if the database has not been initialized.
     */
    public abstract List<Tweet> getAllTweets() throws IllegalStateException;
}

