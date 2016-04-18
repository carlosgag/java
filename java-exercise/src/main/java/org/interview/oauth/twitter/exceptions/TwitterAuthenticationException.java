package org.interview.oauth.twitter.exceptions;

public class TwitterAuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142374240122792709L;

	public TwitterAuthenticationException() {
		super();
	}
	
	public TwitterAuthenticationException(final String message) {
		super(message);
	}
	
	public TwitterAuthenticationException(final String message, final Throwable t) {
		super(message, t);
	}
	
	public TwitterAuthenticationException(final Throwable t) {
		super(t);
	}
}
