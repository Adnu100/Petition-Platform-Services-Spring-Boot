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
            .id(rs.getString(1))
            .title(rs.getString(2))
            .content(rs.getString(3))
            .status(rs.getString(4))
            .response(rs.getString(5))
            .petitionerEmail(rs.getString(6))
            .petitionerName(rs.getString(7))
            .petitionerBioId(rs.getString(8))
            .createTimestamp(rs.getTimestamp(9))
            .signs(rs.getInt(10))
            .saved(rs.getBoolean(11))
            .signed(rs.getBoolean(12))
            .build();
    }

}
