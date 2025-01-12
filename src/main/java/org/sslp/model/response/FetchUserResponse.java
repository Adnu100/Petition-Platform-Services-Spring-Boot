package org.sslp.model.response;

import lombok.Getter;

@Getter
public class FetchUserResponse extends ApiResponse {

    private record User(String name, String dob, String email) { }

    private final User data;

    public FetchUserResponse(String name, String dob, String email) {
        super(SUCCESS, null);
        this.data = new User(name, dob, email);
    }

}
