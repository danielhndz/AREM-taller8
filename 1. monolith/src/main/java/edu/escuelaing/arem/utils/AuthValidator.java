package edu.escuelaing.arem.utils;

import java.util.Base64;

import edu.escuelaing.arem.service.UserService;

public class AuthValidator {

    public static final String AUTH_HEADER = "Authorization";

    private AuthValidator() {
    }

    public static String getUsernameFromB64Token(String token) {
        try {
            return (new String(Base64.getDecoder().decode(token))
                    .split(":"))[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validateB64Token(UserService userService, String token) {
        String username = null;
        String passwd = null;
        int cont = 0;
        for (String data : new String(Base64.getDecoder().decode(token)).split(":")) {
            if (cont == 0) {
                username = data;
                cont++;
            } else if (cont == 1) {
                passwd = data;
            } else {
                break;
            }
        }
        return username != null && passwd != null
                && userService.getUserByUsername(username).getPassword().equals(passwd);
    }
}
