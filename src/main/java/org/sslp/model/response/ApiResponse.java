package org.sslp.model.response;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    private final String status;
    private final String message;

    ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ApiResponse success(String message) {
        return new ApiResponse(SUCCESS, message);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(ERROR, message);
    }

    public ResponseEntity<? extends ApiResponse> toResponseEntity() {
        if(status.equals(SUCCESS))
            return ResponseEntity.ok(this);
        return ResponseEntity.internalServerError().body(new ApiResponse(status, message));
    }

    public ResponseEntity<? extends ApiResponse> toResponseEntity(int statusCode) {
        return ResponseEntity.status(statusCode).body(this);
    }

}
