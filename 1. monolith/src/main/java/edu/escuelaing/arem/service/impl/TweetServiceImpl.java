package edu.escuelaing.arem.service.impl;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.escuelaing.arem.model.Tweet;
import edu.escuelaing.arem.service.TweetService;
import edu.escuelaing.arem.utils.InMemoryData;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TweetServiceImpl implements TweetService {

    private final Set<Tweet> tweets = Collections.synchronizedSet(new LinkedHashSet<>());
    private int idCounter = 1;

    public TweetServiceImpl() {
        for (Tweet tweet : InMemoryData.getTweets()) {
            saveTweet(tweet);
        }
    }

    @Override
    public Set<Tweet> getAllTweets() {
        return tweets;
    }

    @Override
    public void saveTweet(Tweet tweet) {
        tweets.add(tweet);
        idCounter++;
    }

    @Override
    public int getIdCounter() {
        return idCounter;
    }

}
