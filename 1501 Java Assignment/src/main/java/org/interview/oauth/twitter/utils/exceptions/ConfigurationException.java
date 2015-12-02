package org.interview.oauth.twitter.utils.exceptions;

/**
 * Created by carlos.gagliardi on 01/12/2015.
 */
public class ConfigurationException extends Exception {

    public ConfigurationException() {
        super();
    }

    public ConfigurationException(final String message) {
        super(message);
    }

    public ConfigurationException(final String message, final Throwable t) {
        super(message, t);
    }

    public ConfigurationException(final Throwable t) {
        super(t);
    }
}
