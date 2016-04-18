package org.interview.oauth.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.interview.utils.PropertiesLoader;
import org.interview.utils.exceptions.ConfigurationException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSecurity {

	private String consumerKey;
	private String consumerSecret;
	private AccessToken accessToken;
	private Properties properties;

	public TwitterSecurity() throws TwitterException, IOException, ConfigurationException {
		properties = new PropertiesLoader().getProperties();
		consumerKey = properties.getProperty("oauth.consumerKey");
		consumerSecret = properties.getProperty("oauth.consumerSecret");
		setAccessToken();
	}

	public Configuration getConfiguration() {

		ConfigurationBuilder configuration = new ConfigurationBuilder();
		configuration.setOAuthConsumerKey(consumerKey);
		configuration.setOAuthConsumerSecret(consumerSecret);
		configuration.setOAuthAccessToken(accessToken.getToken());
		configuration.setOAuthAccessTokenSecret(accessToken.getTokenSecret());

		return configuration.build();
	}

	private void setAccessToken() 
			throws TwitterException, IOException {
		// The factory instance is re-useable and thread safe.
		Twitter twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken = twitter.getOAuthRequestToken();
		accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			System.out.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			System.out.print("Enter the PIN(if available) or just hit enter.[PIN]:");
			String pin = br.readLine();
			try {
				if (pin.length() > 0) {
					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
	}

}
