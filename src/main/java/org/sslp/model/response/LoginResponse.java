package org.sslp.model.response;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class LoginResponse extends ApiResponse {

    private final String token;
    private final String name;

    public LoginResponse(String token, String name) {
        super(SUCCESS, "successfully logged in");
        this.token = token;
        this.name = name;
    }

    @Override
    public ResponseEntity<? extends ApiResponse> toResponseEntity() {
        return ResponseEntity.status(201).body(this);
    }

}
