package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TweetModel implements Serializable {

	private int id;
	private int userId;
	private String tweet;
	private int isDeleted;
	private Timestamp createDateTime;
	private Timestamp upDateTime;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getuserId() {
		return this.userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public String getTweet() {
		return this.tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public int getisDeleted() {
		return this.isDeleted;
	}

	public void setisDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getcreateDateTime() {
		return this.createDateTime;
	}

	public void setcreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Timestamp getupDateTime() {
		return this.upDateTime;
	}

	public void setupDateTime(Timestamp upDateTime) {
		this.upDateTime = upDateTime;
	}

	}

