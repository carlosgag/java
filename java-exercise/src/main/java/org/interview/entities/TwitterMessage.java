package org.interview.entities;

import twitter4j.User;

/**
 * Created by carlos.gagliardi on 25/11/2015.
 */
public class TwitterMessage implements Comparable<TwitterMessage> {

	private Long id;
	private long created_at;
	private String text;
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Id: " + id + "\n" + 
				"Created_at: " + created_at + "\n" + 
				"Text: " + text + "\n" + 
				"User: " + user.getId() + " - " + 
				user.getCreatedAt().getTime() + " - " + 
				user.getName() + " - " + 
				user.getScreenName() + "\n";
	}

	@Override
	public int compareTo(TwitterMessage m1) {
		long compareCreationDate = ((TwitterMessage) m1).getCreated_at();
		return (int) (this.created_at - compareCreationDate);
	}

}
