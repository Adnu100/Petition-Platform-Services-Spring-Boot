package org.sslp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class User {
    private String name;
    private Date birthday;
    private String token;
    private String email;
    private String bioid;
    private String password;
}
