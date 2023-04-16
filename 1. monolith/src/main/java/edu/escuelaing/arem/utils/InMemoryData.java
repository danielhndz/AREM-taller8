package edu.escuelaing.arem.utils;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.escuelaing.arem.model.Tweet;
import edu.escuelaing.arem.model.User;

public class InMemoryData {

    public static final User DANIEL_USER = new User(
            1, "daniel", "daniel@pm.me", "12345");
    public static final User FELIPE_USER = new User(
            2, "felipe", "felipe@pm.me", "54321");

    private InMemoryData() {
    }

    public static Set<Tweet> getTweets() {
        Set<Tweet> tweets = new LinkedHashSet<>();
        for (int i = 0; i < 15; i++) {
            tweets.add(new Tweet(
                    i + 1, "Este es el tweet #" + (i + 1),
                    i % 2 == 0 ? DANIEL_USER : FELIPE_USER,
                    LocalDateTime.of(2023, 4, i + 1, 20, 12, i)));
        }
        return tweets;
    }
}
