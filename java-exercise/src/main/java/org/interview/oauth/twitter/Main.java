package org.interview.oauth.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class Main {
	
	private final String CONSUMER_KEY = "RLSrphihyR4G2UxvA0XBkLAdl";
	private final String CONSUMER_SECRET = "FTz2KcP1y3pcLw0XXMX5Jy3GTobqUweITIFy4QefullmpPnKm4";
	private String token;
	private String tokenSecret;
	private int numOfTweets;
	private List<TwitterMessage> msgs;
	private final Object lock = new Object();
	private long startTime;
	private long endTime;
	
	public static void main(String[] args) {
		try {
			Main m = new Main();
			m.example();
		} catch (TwitterException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Main() throws TwitterException, IOException{
		numOfTweets = 0;
		msgs = new ArrayList<>();
		getAccessToken();
	}
	
	public void example() throws TwitterException{
		
		// Configuration
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder
				.setOAuthConsumerKey(CONSUMER_KEY)
				.setOAuthConsumerSecret(CONSUMER_SECRET)
				.setOAuthAccessToken(this.token)
				.setOAuthAccessTokenSecret(this.tokenSecret);
	
		// Initiate
		TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
		twitterStream.addListener(new StatusListener() {

			@Override
			public void onException(Exception ex) {
				System.out.println(ex.getMessage());
			}

			@Override
			public void onStatus(Status status) {
				numOfTweets++;
				TwitterMessage msg = new TwitterMessage();
				msg.setId(status.getId());
				msg.setCreated_at(status.getCreatedAt().getTime());
				msg.setText(status.getText());
				msg.setUser(status.getUser());
				msgs.add(msg);
				System.out.println(numOfTweets);
				if(numOfTweets == 100 || (System.currentTimeMillis() > endTime)){
		            synchronized (lock) {
		              lock.notify();
		            }
		            System.out.println("unlocked & the process finished with " + numOfTweets + " tweets");
				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println(statusDeletionNotice.toString());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Number of limited statuses: " + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("User id: " + userId + " up to status id: " + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("Warning: " + warning.getMessage());
			}

		});
		
		// Filtering
		FilterQuery tweetFilterQuery = new FilterQuery();
		tweetFilterQuery.track(new String[] { "Bieber" });
		
		// Initialize time counter
		startTime = System.currentTimeMillis();
		endTime = startTime + 30000; // 30 secs
		
		//	Starting the streaming capture
		twitterStream.filter(tweetFilterQuery);
		
		try {
			synchronized (lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		twitterStream.shutdown();

	}
	public AccessToken getAccessToken() throws TwitterException, IOException{    
		// The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if available) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         }else{
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    //persist to the accessToken for future reference.
	    storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
	    return accessToken;
	}
	private void storeAccessToken(long id, AccessToken accessToken) {
		this.token = accessToken.getToken();
		this.tokenSecret = accessToken.getTokenSecret();		
	}

}
