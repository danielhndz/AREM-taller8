package edu.escuelaing.arem.service;

import java.util.Set;

import edu.escuelaing.arem.model.User;

public interface UserService {

    public Set<User> getAllUser();

    public User getUserByUsername(String username);

    public void saveUser(User user);

    public int getIdCounter();
}
