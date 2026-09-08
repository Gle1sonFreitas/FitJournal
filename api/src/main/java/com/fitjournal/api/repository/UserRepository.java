package com.fitjournal.api.repository;

import com.fitjournal.api.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void cadastrar(String userName, String email, String password){
        String sql = "insert into usuarios(userName, email, password) values(?, ?, ?);";
        try {
            jdbcTemplate.update(sql, userName, email, password);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException(e);
        }

    }

}
