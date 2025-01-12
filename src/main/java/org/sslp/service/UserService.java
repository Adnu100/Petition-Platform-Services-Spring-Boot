package org.sslp.service;

import org.springframework.stereotype.Service;
import org.sslp.model.request.LoginCredentials;
import org.sslp.model.User;

@Service
public class UserService {

    public User validateUserCredentials(LoginCredentials credentials) {
        //TODO: implement code
        if(credentials.email().equals("admin@sslp.com")) {
            return User.builder().build();
        }
        return null;
    }

    public User fetchUser(String bioId) {
        //TODO: implement code
        if(bioId.equals("1")) {
            return User.builder().build();
        }
        return null;
    }

    public boolean registerUser(User user) {
        //TODO: implement code
        return !user.getEmail().equals("admin@sslp.com");
    }

    public void deleteUser(String userName) {
        //TODO: implement code
    }
}
