package org.sslp.utils;

import org.springframework.jdbc.core.RowMapper;
import org.sslp.model.Petition;
import org.sslp.model.User;

import java.util.Map;

public class Mappers {

    public RowMapper<Map<String, String>> loginDetailsMapper(String type) {
        return (rs, rowNum) -> Map.of(
            "fullName", rs.getString("fullname"),
            "passwordHash", rs.getString("password_hash"),
            "type", type
        );
    }

    public RowMapper<User> userMapper() {
        return (rs, rowNum) -> User.builder()
            .email(rs.getString("email"))
            .birthday(rs.getDate("dob"))
            .name(rs.getString("fullname"))
            .build();
    }

    public RowMapper<Petition> petitionMapper() {
        return (rs, rowNum) -> Petition.builder()
            .id(rs.getString(0))
            .title(rs.getString(1))
            .content(rs.getString(2))
            .status(rs.getString(3))
            .response(rs.getString(4))
            .petitionerEmail(rs.getString(5))
            .petitionerName(rs.getString(6))
            .petitionerBioId(rs.getString(7))
            .createTimestamp(rs.getTimestamp(8))
            .signs(rs.getInt(9))
            .saved(rs.getBoolean(10))
            .signed(rs.getBoolean(11))
            .build();
    }

}
