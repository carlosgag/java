package org.interview.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.interview.utils.exceptions.ConfigurationException;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class PropertiesLoader{

    private static final String TWITTER_PROPERTIES = "twitter.properties";

    public Properties getProperties()
            throws ConfigurationException {
        Properties properties = new Properties();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(TWITTER_PROPERTIES);
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new ConfigurationException(e.getMessage(), e.getCause());
        }
        return properties;
    }

}
