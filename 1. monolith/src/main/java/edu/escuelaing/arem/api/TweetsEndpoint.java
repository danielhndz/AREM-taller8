package edu.escuelaing.arem.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.escuelaing.arem.model.Tweet;
import edu.escuelaing.arem.service.TweetService;
import edu.escuelaing.arem.service.UserService;
import edu.escuelaing.arem.utils.AuthValidator;
import jakarta.inject.Inject;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

@Path("/api/tweets")
public class TweetsEndpoint {

    @Inject
    UserService userService;

    @Inject
    TweetService tweetService;

    @GET
    public List<Tweet> getTweets() {
        ArrayList<Tweet> arrayList = new ArrayList<>(tweetService.getAllTweets());
        Collections.sort(arrayList,
                (t1, t2) -> t2.getCreatedAt().compareTo(t1.getCreatedAt()));
        return arrayList;
    }

    @POST
    public Response saveTweet(@Context HttpHeaders headers, @FormParam("tweet") String tweet) {
        String authToken = null;
        try {
            authToken = headers.getRequestHeader(AuthValidator.AUTH_HEADER).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (authToken != null && AuthValidator.validateB64Token(userService, authToken)) {
            try {
                tweetService.saveTweet(new Tweet(
                        tweetService.getIdCounter(), tweet,
                        userService.getUserByUsername(
                                AuthValidator.getUsernameFromB64Token(authToken)),
                        LocalDateTime.of(2023, 4, 16, 12, 16)));
                return Response.ok().build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        }
        return Response.status(401).build();
    }
}
