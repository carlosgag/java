package org.interview.oauth.twitter.exceptions;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class TwitterFormattingException extends Exception {

    public TwitterFormattingException() {
        super();
    }

    public TwitterFormattingException(final String message) {
        super(message);
    }

    public TwitterFormattingException(final String message, final Throwable t) {
        super(message, t);
    }

    public TwitterFormattingException(final Throwable t) {
        super(t);
    }
}
