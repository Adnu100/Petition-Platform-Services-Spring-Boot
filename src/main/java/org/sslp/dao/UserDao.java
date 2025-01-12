package org.sslp.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.sslp.constants.Queries;
import org.sslp.model.User;
import org.sslp.utils.Mappers;

import java.util.Map;

@SuppressWarnings("deprecation")
@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final Mappers mappers;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mappers = new Mappers();
    }

    public Map<String, String> checkLogin(String email) {
        try {
            return jdbcTemplate.queryForObject(
                Queries.CHECK_LOGIN, new Object[] {email}, mappers.loginDetailsMapper("user")
            );
        } catch(EmptyResultDataAccessException e) {
            return jdbcTemplate.queryForObject(
                Queries.CHECK_ADMIN_LOGIN, new Object[] {email}, mappers.loginDetailsMapper("admin")
            );
        }
    }

    public User fetchUserByBioId(String bioId) {
        try {
            return jdbcTemplate.queryForObject(Queries.FETCH_PERSON_DATA, new Object[] {bioId}, mappers.userMapper());
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean addUser(String bioId, String email, String passwordHash) {
        try {
            jdbcTemplate.update(Queries.REGISTER_PETITIONER, bioId, email, passwordHash);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
