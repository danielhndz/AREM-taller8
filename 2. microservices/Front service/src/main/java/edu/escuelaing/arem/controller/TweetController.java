package edu.escuelaing.arem.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jboss.resteasy.reactive.RestHeader;

import edu.escuelaing.arem.service.UserService;
import edu.escuelaing.arem.utils.AuthValidator;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/tweet")
public class TweetController {

    private static final String TWEET_URL = "src/main/resources/META-INF/resources/tweet.html";
    private static final String LOGIN_URL = "src/main/resources/META-INF/resources/login.html";
    private static final String ERROR_RESPONSE = "Algo ha salido mal!";

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml(@RestHeader("Authorization") String auth) {
        String path = LOGIN_URL;
        if (auth != null &&
                AuthValidator.validateB64Token(userService, auth)) {
            path = TWEET_URL;
        }
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR_RESPONSE;
        }
    }
}
