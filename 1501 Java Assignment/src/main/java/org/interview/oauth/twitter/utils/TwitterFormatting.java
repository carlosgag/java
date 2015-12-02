package org.interview.oauth.twitter.utils;

import org.interview.oauth.twitter.utils.exceptions.ConfigurationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class TwitterFormatting {


    public static Date getTwitterDate(String date)
            throws ParseException, ConfigurationException {

        Date formattedDate;
        if (date != null) {
            Properties properties = new PropertiesLoader().getProperties();
            SimpleDateFormat sf = new SimpleDateFormat(
                    properties.getProperty("twitter.date.format"), Locale.ENGLISH);
            sf.setLenient(true);
            formattedDate = sf.parse(date);
        } else {
            System.out.println("The created_at date is null, then was assigned actual date");
            formattedDate = new Date();
        }

        return formattedDate;
    }
}
