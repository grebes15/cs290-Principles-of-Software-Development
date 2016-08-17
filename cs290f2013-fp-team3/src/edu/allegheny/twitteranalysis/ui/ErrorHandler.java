package edu.allegheny.twitteranalysis.ui;

public class ErrorHandler {
	private boolean verbose;

	public ErrorHandler(boolean verbose) {
		this.verbose = verbose;
	}
	
	/**
	 * Exits the system forcibly with System.exit() on an error,
	 * possibly displaying the technical cause if the verbose flag is set
	 * 
	 * @param t The cause of the quit, if any.
	 */
	public void equit(Throwable t) {
		if (verbose) {
		    System.err.println("");
			System.err.println("System quitting!");
			maybeprint(t);
		}
		System.exit(1);
	}
	
	/**
	 * Prints a warning to the console, optionally with an error depending on if the verbose flag was set
	 * 
	 * @param usermessage The simple message to show the user
	 * @param t The technical error, shown if the verbose flag is set
	 */
	public void ewarn(String usermessage, Throwable t) {
		System.out.println("Warning: " + usermessage);
		maybeprint(t);
	}
	
	/**
	 * Prints the {@link Throwable} to the console if the verbose flag was set 
	 * Otherwise is a nop
	 * 
	 * @param t The error to show
	 */
	public void maybeprint(Throwable t) {
		if(verbose) {
			System.err.println("Cause:");
			if(t == null) {
				System.err.println("Nothing to show.");
			} else {
				t.printStackTrace();
			}
		}
	}
}
