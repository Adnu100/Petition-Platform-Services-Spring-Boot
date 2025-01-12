package org.sslp.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class User {
    private String name;
    private Date birthday;
    private String token;
    private String email;
    private String bioid;
    private String passwordHash;
}
