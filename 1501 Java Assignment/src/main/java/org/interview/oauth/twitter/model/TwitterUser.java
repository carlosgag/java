package org.interview.oauth.twitter.model;

import com.google.gson.annotations.Expose;
import org.interview.oauth.twitter.exceptions.TwitterFormattingException;
import org.interview.oauth.twitter.utils.TwitterFormatting;
import org.interview.oauth.twitter.utils.exceptions.ConfigurationException;

import java.text.ParseException;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class TwitterUser {

    @Expose
    private Long id;

    @Expose
    private String created_at;

    private Long created_at_epoch;

    @Expose
    private String name;

    @Expose
    private String screen_name;

    @Override
    public String toString() {
        return "\nUser id: " + id + "\n" +
                " created_at: " + created_at_epoch + "\n" +
                " name: " + name + "\n" +
                " screen_name: " + screen_name + "\n"
                ;
    }

    public int compareTo(TwitterUser u1) {
        return this.created_at_epoch.compareTo(u1.created_at_epoch);
    }

    public void formatDate()
            throws TwitterFormattingException,ConfigurationException {
        try {
            created_at_epoch = TwitterFormatting.getTwitterDate(created_at).getTime();
        } catch (ParseException e) {
            throw new TwitterFormattingException(
                    "Couldn't reformat users's created_at: " + created_at, e.getCause());
        }
    }
}
