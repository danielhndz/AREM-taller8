package edu.escuelaing.arem.service;

import java.util.Set;

import edu.escuelaing.arem.model.Tweet;

public interface TweetService {

    public Set<Tweet> getAllTweets();

    public void saveTweet(Tweet tweet);

    public int getIdCounter();
}
