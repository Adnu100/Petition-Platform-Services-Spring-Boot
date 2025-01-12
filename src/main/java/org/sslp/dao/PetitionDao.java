package org.sslp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.sslp.constants.Queries;
import org.sslp.model.Petition;
import org.sslp.utils.Mappers;

import java.util.List;

@SuppressWarnings("deprecation")
@Repository
public class PetitionDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Petition> petitionRowMapper;

    public PetitionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.petitionRowMapper = new Mappers().petitionMapper();
    }

    public Petition findById(String id, String petitioner) {
        return jdbcTemplate.queryForObject(Queries.FETCH_BY_ID, new Object[] {petitioner, petitioner, id}, petitionRowMapper);
    }

    public List<Petition> findAll(String petitioner) {
        return jdbcTemplate.query(Queries.FETCH_ALL, new Object[] {petitioner, petitioner}, petitionRowMapper);
    }

    public List<Petition> findAll(String petitioner, int pageNumber, int pageSize) {
        return jdbcTemplate.query(
            Queries.FETCH_ALL_PAGINATION, new Object[] {petitioner, petitioner, pageNumber, pageSize}, petitionRowMapper
        );
    }

    public List<Petition> findSaved(String petitioner) {
        return jdbcTemplate.query(Queries.FETCH_BY_SAVED, new Object[] {petitioner, petitioner}, petitionRowMapper);
    }

    public List<Petition> findSaved(String petitioner, int pageNumber, int pageSize) {
        return jdbcTemplate.query(
            Queries.FETCH_BY_SAVED_PAGINATION, new Object[] {petitioner, petitioner, pageNumber, pageSize}, petitionRowMapper
        );
    }

    public List<Petition> findSigned(String petitioner) {
        return jdbcTemplate.query(Queries.FETCH_BY_SIGN, new Object[] {petitioner, petitioner}, petitionRowMapper);
    }

    public List<Petition> findSigned(String petitioner, int pageNumber, int pageSize) {
        return jdbcTemplate.query(
            Queries.FETCH_BY_SIGN_PAGINATION, new Object[] {petitioner, petitioner, pageNumber, pageSize}, petitionRowMapper
        );
    }

    public List<Petition> findByPetitioner(String petitioner, String petitionerBy) {
        return jdbcTemplate.query(Queries.FETCH_BY_PETITIONER, new Object[] {petitioner, petitioner, petitionerBy}, petitionRowMapper);
    }

    public List<Petition> findByPetitioner(String petitioner, String petitionerBy, int pageNumber, int pageSize) {
        return jdbcTemplate.query(
            Queries.FETCH_BY_PETITIONER_PAGINATION,
            new Object[] {petitioner, petitioner, petitionerBy, pageNumber, pageSize},
            petitionRowMapper
        );
    }

    public void createPetition(String petitioner, String title, String content) {
        jdbcTemplate.update(Queries.CREATE, petitioner, title, content);
    }

    public void signPetition(String id, String by) {
        jdbcTemplate.update(Queries.SIGN, id, by);
    }

    public void savePetition(String id, String by) {
        jdbcTemplate.update(Queries.SAVE, id, by);
    }

    public void unsavePetition(String id, String by) {
        jdbcTemplate.update(Queries.UNSAVE, id, by);
    }

    public void closePetitionWithResponse(String id, String response) {
        jdbcTemplate.update(Queries.CLOSE_WITH_RESPONSE, response, id);
    }

    public void updatePetitionContent(String id, String email, String content) {
        jdbcTemplate.update(Queries.UPDATE_CONTENT, content, email, id);
    }

}
