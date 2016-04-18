package org.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.interview.entities.TwitterMessage;
import org.interview.oauth.twitter.TwitterSecurity;
import org.interview.utils.PropertiesLoader;
import org.interview.utils.exceptions.ConfigurationException;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterStreaming {
	
	private int numOfTweets;
	private List<TwitterMessage> msgs;
	private final Object lock = new Object();
	private long startTime;
	private long endTime;
	private Properties properties;
	
	public TwitterStreaming() throws TwitterException, IOException, ConfigurationException{
		properties = new PropertiesLoader().getProperties();
		msgs = new ArrayList<>();
	}
	
	public List<TwitterMessage> getMessages(){
		return this.msgs;
	}
	
	public void captureStreamTweets() throws TwitterException, IOException, ConfigurationException{
	
		// Initiate
		TwitterSecurity security = new TwitterSecurity();
		TwitterStream twitterStream = new TwitterStreamFactory(security.getConfiguration()).getInstance();
		
		// Create listener
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
				int topNumber = Integer.valueOf(properties.getProperty("twitter.messages.topnumber"));
				long currentTime = System.currentTimeMillis();
				if ((numOfTweets == topNumber) || (currentTime > endTime)) {
					synchronized (lock) {
						lock.notify();
					}
					System.out.println("unlocked & the process finished with " + numOfTweets + " tweets stored");
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
		tweetFilterQuery.track(
				new String[] { 
						properties.getProperty("twitter.track.searchtext") 
				});
		
		// Initialize time counter
		startTime = System.currentTimeMillis();
		int limitTime = Integer.valueOf(properties.getProperty("twitter.stream.limittime"));
		endTime = startTime + limitTime;
		
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

}
