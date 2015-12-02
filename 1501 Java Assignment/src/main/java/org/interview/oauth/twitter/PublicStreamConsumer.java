package org.interview.oauth.twitter;

import com.google.api.client.http.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.interview.oauth.twitter.exceptions.TwitterAuthenticationException;
import org.interview.oauth.twitter.exceptions.TwitterFormattingException;
import org.interview.oauth.twitter.model.TwitterMessage;
import org.interview.oauth.twitter.model.TwitterUser;
import org.interview.oauth.twitter.utils.PropertiesLoader;
import org.interview.oauth.twitter.utils.exceptions.ConfigurationException;

import java.io.*;
import java.util.*;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class PublicStreamConsumer extends Thread {

    private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";
    //this is 200 bytes per tweet multiplied by the amount of messages
    private static final String BYTES_AMOUNT = "20000";
    private static final int NUM_OF_MSGS = 100;
    private static final int READ_TIMEOUT = 30000;
    private final Map<TwitterUser, List<TwitterMessage>> map = new HashMap<>();

    private HttpRequest getRequest(PrintStream out)
            throws TwitterAuthenticationException, IOException, ConfigurationException {

        Properties properties = new PropertiesLoader().getProperties();

        TwitterAuthenticator authenticator = new TwitterAuthenticator(
                out,
                properties.getProperty("oauth.consumerKey"),
                properties.getProperty("oauth.consumerSecret")
        );

        HttpRequestFactory factory = authenticator.getAuthorizedHttpRequestFactory();

        out.println("Connecting ...");
        HttpRequest request = factory.buildGetRequest(new GenericUrl(STREAM_URI));
        request.getHeaders().put("version", "HTTP/1.1");
        request.getHeaders().put("host", "stream.twitter.com");
        request.getUrl().put("track", properties.getProperty("twitter.track.text"));
        //parameter count depends of the role of the user,
        // for the dev user, I can't use it. Was requested to twitter API team a different role
        //request.getUrl().put("count", 100);
        request.getUrl().put("delimited", BYTES_AMOUNT);
        out.println(request.getUrl());
        return request;
    }

    private void processResponse(InputStream is) {

        try {
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            int msgCounter = 0;
            String read = br.readLine();
            while (read != null && msgCounter < NUM_OF_MSGS) {
                System.out.print(".");
                msgCounter++;
                parseTweet(read);
                //next msg
                read = br.readLine();
            }
        } catch (IOException | TwitterFormattingException | ConfigurationException e) {
            System.out.println("Error processing response");
        }
    }

    public void run() {
        try (PrintStream out = System.out) {

            out.println("Starting twitter public stream consumer");

            HttpRequest request = getRequest(out);
            request.setReadTimeout(READ_TIMEOUT);

            //initial date
            Date dt1 = new Date();
            HttpResponse response = request.execute();
            Date dt2 = new Date();
            long timer = ((dt2.getTime() - dt1.getTime()));

            InputStream is = response.getContent();

            //store results
            out.println("Processing messages ");
            processResponse(is);
            out.println();
            out.println("Processed " + map.size() + " messages of the obtained in " + timer + " miliseconds");

            TreeMap<TwitterUser, List<TwitterMessage>> treeMap = sortingMap();
            out.println(treeMap);

        } catch (TwitterAuthenticationException e) {
            System.out.println("Error authenticating to twitter");
        } catch (IOException e) {
            System.out.println("Error with the connection with twitter");
        } catch (ConfigurationException e) {
            System.out.println("Error in the twitter configuration");
        }

    }

    private Gson getGson() {

        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        return builder.create();
    }

    private void parseTweet(String text)
            throws ConfigurationException, TwitterFormattingException, JsonSyntaxException {
        try {
            final TwitterMessage msg = getGson().fromJson(text, TwitterMessage.class);
            msg.formatDate();
            if (map.containsKey(msg.getUser())) {
                //the user is in the map, should add the new message
                List<TwitterMessage> userMsgs = map.get(msg.getUser());
                userMsgs.add(msg);
            } else {
                TwitterUser twitterUser = msg.getUser();
                twitterUser.formatDate();
                List<TwitterMessage> userMsgs = new ArrayList<>();
                userMsgs.add(msg);
                map.put(twitterUser, userMsgs);
            }
        } catch (JsonSyntaxException e) {
            //capturing line parsing errors
            System.out.println("Error when parsing tweet from line");
        }
    }

    private TreeMap<TwitterUser, List<TwitterMessage>> sortingMap() {
        //sort msgs for each user
        for (Map.Entry<TwitterUser, List<TwitterMessage>> entry : map.entrySet()) {
            List<TwitterMessage> msgList = entry.getValue();
            Collections.sort(msgList);
            entry.setValue(msgList);
        }
        //sort users
        TreeMap<TwitterUser, List<TwitterMessage>> treeMap =
                new TreeMap<>(
                        (u1, u2) -> {
                            return u1.compareTo(u2);
                        });
        treeMap.putAll(map);

        return treeMap;

    }

}
