package edu.allegheny.twitteranalysis;

import java.util.Date;

public class Tweet {
    /**
	 * @param text
	 * @param timestamp
	 * @param id
	 * @param source
	 * @param reply_to_id
	 * @param retweeted_status_id
	 * @param retweeted_status_user_id
	 */
	public Tweet(String text, Date timestamp, String id, String source, String reply_to_id,
			String retweeted_status_id, String retweeted_status_user_id) {
		this.id = id;
		this.reply_to_id = reply_to_id;
		this.source = source;
		this.retweeted_status_id = retweeted_status_id;
		this.retweeted_status_user_id = retweeted_status_user_id;
		this.text = text;
		this.timestamp = timestamp;
	}
	/**
	 * Overload constructor which provides default values of the empty string for reply_to_id, retweeted_status_id, and retweeted_status_user_id
	 *
	 * @param text
	 * @param timestamp
	 * @param id
	 * @param source
	 */
	public Tweet(String text, Date timestamp, String id, String source) {
		this(text, timestamp, id, source, "", "", "");
	}

	private String id;
    private String reply_to_id;
    private String source;
    private String retweeted_status_id;
    private String retweeted_status_user_id;
    private String text;
    private Date timestamp;
    
    /**
     * Check equality of this Tweet object with another Tweet object by comparing all instance variables
     *
     * @param t The tweet to compare to
     */
    @Override
    public boolean equals(Object t) {
    	if(!(t instanceof Tweet)) return false;
    	Tweet to = (Tweet) t;
    	return (this.id.equals(to.id)
    			&& this.reply_to_id.equals(to.reply_to_id)
    			&& this.source.equals(to.source)
    			&& this.retweeted_status_id.equals(to.retweeted_status_id)
    			&& this.retweeted_status_user_id.equals(to.retweeted_status_user_id)
    			&& this.timestamp.equals(to.timestamp)
    			&& this.text.equals(to.text));
    			
    }

    /** 
     * @return Message
     */
    public String getText() {
        return text;
    }

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the reply_to_id
	 */
	public String getReply_to_id() {
		return reply_to_id;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the retweeted_status_id
	 */
	public String getRetweeted_status_id() {
		return retweeted_status_id;
	}

	/**
	 * @return the retweeted_status_user_id
	 */
	public String getRetweeted_status_user_id() {
		return retweeted_status_user_id;
	}

	/**
     * Returns the date that the Tweet was returned
     *
     * @return Date
     */
    public Date getTimestamp() {
        return timestamp;
    }
}
