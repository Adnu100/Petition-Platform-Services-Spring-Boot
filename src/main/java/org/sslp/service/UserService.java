package org.sslp.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.sslp.dao.UserDao;
import org.sslp.model.request.LoginCredentials;
import org.sslp.model.User;
import org.sslp.utils.JWTUtils;

import java.util.Map;

@Service
public class UserService {

    private final JWTUtils jwtUtils;
    private final UserDao userDao;

    public UserService(JWTUtils jwtUtils, UserDao userDao) {
        this.jwtUtils = jwtUtils;
        this.userDao = userDao;
    }

    public User validateUserCredentials(LoginCredentials credentials) {
        try {
            Map<String, String> map = userDao.checkLogin(credentials.email());
            String passwordHash = map.get("passwordHash");
            if(passwordHash.equals(credentials.password())) {
                return User.builder()
                    .email(credentials.email())
                    .name(map.get("fullName"))
                    .token(jwtUtils.createJWT(credentials.email(), passwordHash))
                    .build();
            }
            else
                return null;
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User fetchUser(String bioId) {
        return userDao.fetchUserByBioId(bioId);
    }

    public boolean registerUser(User user) {
        return userDao.addUser(user.getBioid(), user.getEmail(), user.getPasswordHash());
    }

}
