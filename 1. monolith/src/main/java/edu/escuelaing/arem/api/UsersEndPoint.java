package edu.escuelaing.arem.api;

import java.util.Base64;
import java.util.Set;

import edu.escuelaing.arem.model.User;
import edu.escuelaing.arem.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
public class UsersEndPoint {

    @Inject
    UserService userService;

    @GET
    public Set<User> getUsers() {
        return userService.getAllUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) {
        try {
            userService.saveUser(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Path("/login")
    @POST
    public Response login(
            @FormParam("username") String username,
            @FormParam("passwd") String passwd) {
        try {
            User user = userService.getUserByUsername(username);
            if (user != null && (user.getPassword().equals(passwd))) {
                return Response
                        .ok(Base64.getEncoder().encodeToString(
                                (username + ":" + passwd).getBytes()))
                        .build();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response
                .status(401)
                .entity("no match")
                .build();
    }
}
