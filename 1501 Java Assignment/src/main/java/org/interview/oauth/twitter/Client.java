package org.interview.oauth.twitter;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class Client {
    public static void main(String[] args) {

        final PublicStreamConsumer consumer = new PublicStreamConsumer();
        // final because we will later pull the latest Tweet
        consumer.start();

    }

}
