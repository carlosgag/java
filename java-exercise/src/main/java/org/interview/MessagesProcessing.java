package org.interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.interview.entities.TwitterMessage;
import org.interview.utils.exceptions.ConfigurationException;

import twitter4j.TwitterException;
import twitter4j.User;

public class MessagesProcessing {

	public static void main(String[] args) {
		try {
			TwitterStreaming m = new TwitterStreaming();
			m.captureStreamTweets();
			List<TwitterMessage> messages = m.getMessages();
			for (TwitterMessage twitterMessage : messages) {
				System.out.println("***");
				System.out.println(twitterMessage);
			}
			// Group messages by user
			Map<User, List<TwitterMessage>> byUser = messages.stream()
					.collect(Collectors.groupingBy(TwitterMessage::getUser));
			// sort the map by key & comparator
			TreeMap<User, List<TwitterMessage>> sorted = new TreeMap<User, List<TwitterMessage>>(
					new Comparator<User>() {
						@Override
						public int compare(User u1, User u2) {
							return (u1.getCreatedAt()).compareTo(u2.getCreatedAt());
						}
					});
			sorted.putAll(byUser);
			for (Map.Entry<User, List<TwitterMessage>> entry : sorted.entrySet()) {
				User user = entry.getKey();
				List<TwitterMessage> messagesForUser = entry.getValue();
				// will sort used the compareTo implementation for the object TwitterMessage
				Collections.sort(messagesForUser);
				sorted.put(user, messagesForUser);
			}

			for (Map.Entry<User, List<TwitterMessage>> entry : sorted.entrySet()) {
				User user = entry.getKey();
				System.out.println(	"User: " + user.getId() + " - " + 
						user.getCreatedAt().getTime() + " - " + 
						user.getName() + " - " + 
						user.getScreenName());
				for (TwitterMessage twitterMessage : entry.getValue()) {
					System.out.println(">>>");
					System.out.println(twitterMessage);
				}
			}
		} catch (TwitterException | IOException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
