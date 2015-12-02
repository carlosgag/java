package org.interview.oauth.twitter.model;

import com.google.gson.annotations.Expose;
import org.interview.oauth.twitter.exceptions.TwitterFormattingException;
import org.interview.oauth.twitter.utils.TwitterFormatting;
import org.interview.oauth.twitter.utils.exceptions.ConfigurationException;

import java.text.ParseException;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class TwitterMessage implements Comparable<TwitterMessage> {

    @Expose
    private Long id;

    @Expose
    private String created_at;

    private Long created_at_epoch;

    @Expose
    private String text;

    @Expose
    private TwitterUser user;

    @Override
    public String toString() {
        return "\nMsg id: " + id + "\n" +
                " created_at: " + created_at_epoch + "\n" +
                " text: " + text + "\n" +
                " user: " + user + "\n"
                ;
    }

    public int compareTo(TwitterMessage m1) {
        return this.created_at_epoch.compareTo(m1.created_at_epoch);
    }

    public TwitterUser getUser() {
        return user;
    }

    public void formatDate()
            throws TwitterFormattingException, ConfigurationException {
        try {
            created_at_epoch = TwitterFormatting.getTwitterDate(created_at).getTime();
        } catch (ParseException e) {
            throw new TwitterFormattingException(
                    "Couldn't reformat msg's created_at: " + created_at, e.getCause());
        }
    }
}
