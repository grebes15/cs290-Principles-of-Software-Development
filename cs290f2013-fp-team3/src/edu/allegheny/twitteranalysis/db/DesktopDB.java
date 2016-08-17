package edu.allegheny.twitteranalysis.db;

import edu.allegheny.twitteranalysis.Tweet;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DesktopDB extends DBConnection
{
    private static Connection conn;
    
    @Override
    public void init() throws ConnectionFailedException {
    	Statement stat = null;
        try {
            // Set up driver for connection to the database
            Class.forName("org.sqlite.JDBC");
            // Set up connection to the database.
            // hsqldb.write_delay=false and shutdown=true are modifiers that require the
            // connection to immediately update the script file
            conn = DriverManager.getConnection("jdbc:sqlite:TwitterDatabase.db");
            
            stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Tweets "
                    + "(Tweet varchar(140), DatePosted varchar(30), id varchar(30) PRIMARY KEY, "
                    + "source varchar(200), reply_to_id varchar(30), retweeted_status_id varchar(30), "
                    + "retweeted_status_user_id varchar(30));");
            stat.close();
        } catch(Exception e) {
            throw new ConnectionFailedException(e);
        }
    }
    
    @Override
    public void clearDB() throws IllegalStateException {
    	try {
    		Statement stat = conn.createStatement();
    		stat.execute("DROP TABLE IF EXISTS Tweets;");
    		stat.execute("CREATE TABLE Tweets "
                    + "(Tweet varchar(140), DatePosted varchar(140), id varchar(30) PRIMARY KEY, "
                    + "source varchar(200), reply_to_id varchar(30), retweeted_status_id varchar(30),"
                    + "retweeted_status_user_id varchar(30));");
    		stat.close();
    	} catch (Exception e) {
    		//TODO: Catch more specific exceptions
    		throw new IllegalStateException(e);
    	}
    }

    @Override
    public void insertTweets(Tweet... T) throws IllegalStateException {
        // Use a PreparedStatement to add variable contents to the statement
        try {
        	            /*PreparedStatement prep = conn.prepareStatement(
        	                    "INSERT INTO Tweets (Tweet, DatePosted, id, source, reply_to_id, retweeted_status_id, retweeted_status_user_id) VALUES (?, ?, ?, ?, ?, ?, ?) " +
        	                    "WHERE NOT EXISTS(SELECT id FROM Tweets WHERE id = ?);");*/
            PreparedStatement prep = conn.prepareStatement(
                    "INSERT OR IGNORE INTO Tweets(Tweet, DatePosted, id, source, reply_to_id, retweeted_status_id, retweeted_status_user_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            // Prepare and execute the statement using the message and date from each Tweet
            // in the provided array
            for(int i = 0; i < T.length; i++) {
                prep.setString(1, T[i].getText());
                prep.setString(2, Long.toString(T[i].getTimestamp().getTime()));
                prep.setString(3, T[i].getId());
                prep.setString(4, T[i].getSource());
                prep.setString(5, T[i].getReply_to_id());
                prep.setString(6, T[i].getRetweeted_status_id());
                prep.setString(7, T[i].getRetweeted_status_user_id());
                prep.execute();
            }

            // Close the PreparedStatement
            prep.close();
        } catch(Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws IllegalStateException {
        try {
            conn.close();
        } catch(SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Tweet> getAllTweets() throws IllegalStateException {
    	Statement stat = null;
    	Tweet temp;
    	ArrayList<Tweet> ret = new ArrayList<Tweet>();
        
    	try {
            stat = conn.createStatement();
        } catch(SQLException e) {
        	throw new IllegalStateException(e);
        }
        
        ResultSet res = null;
        try {
            if(stat.execute("SELECT * FROM Tweets;")) {
            	res = stat.getResultSet();
            	while(res.next()) {
                    temp = new Tweet(res.getString(1), new Date(Long.parseLong(res.getString(2))), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7));
                    ret.add(temp);
                }
            }
            return ret;
        } catch(NullPointerException e) {
        	throw new IllegalStateException ("Could not execute SQL statement! (Is the database closed?)", e);
        } catch (NumberFormatException e) {
			throw new IllegalStateException(e);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
            
        
    }
}
