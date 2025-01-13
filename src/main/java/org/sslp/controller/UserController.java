package org.sslp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sslp.model.*;
import org.sslp.model.request.LoginCredentials;
import org.sslp.model.response.ApiResponse;
import org.sslp.model.response.FetchUserResponse;
import org.sslp.model.response.LoginResponse;
import org.sslp.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<? extends ApiResponse> login(@RequestBody LoginCredentials credentials) {
        User user = userService.validateUserCredentials(credentials);
        return user != null ?
            new LoginResponse(user.getToken(), user.getName()).toResponseEntity() :
            ApiResponse.error("user email or password incorrect").toResponseEntity(401);
    }

    @PostMapping("/fetch-data")
    public ResponseEntity<? extends ApiResponse> fetchData(@RequestBody Map<String, String> params) {
        User user = userService.fetchUser(params.get("bioid"));
        if(user != null)
            return new FetchUserResponse(
                user.getName(), user.getBirthday().toString(), user.getEmail()
            ).toResponseEntity();
        return ApiResponse.error("wrong bioid provided").toResponseEntity(400);
    }

    @PostMapping("/register")
    public ResponseEntity<? extends ApiResponse> registerUser(@RequestBody User user) {
        if(userService.registerUser(user))
            return ApiResponse.success("user registered successfully").toResponseEntity();
        return ApiResponse.error("user already exists").toResponseEntity(400);
    }

}
