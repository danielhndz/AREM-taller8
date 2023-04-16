package edu.escuelaing.arem.service.impl;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.escuelaing.arem.model.User;
import edu.escuelaing.arem.service.UserService;
import edu.escuelaing.arem.utils.InMemoryData;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final Set<User> users = Collections.synchronizedSet(new LinkedHashSet<>());
    private int idCounter = 1;

    public UserServiceImpl() {
        saveUser(InMemoryData.DANIEL_USER);
        saveUser(InMemoryData.FELIPE_USER);
    }

    @Override
    public Set<User> getAllUser() {
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
        idCounter++;
    }

    @Override
    public int getIdCounter() {
        return idCounter;
    }

}
