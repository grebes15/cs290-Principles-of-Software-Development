package edu.allegheny.twitteranalysis.db;

public class ConnectionFailedException extends Exception {
	private static final long serialVersionUID = -8681489933993238127L;
	
	public ConnectionFailedException() {
		super();
	}
	
	public ConnectionFailedException(Throwable cause) {
		super(cause);
	}
}
