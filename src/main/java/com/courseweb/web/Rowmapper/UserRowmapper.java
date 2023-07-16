package com.courseweb.web.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.courseweb.web.Model.User;

public class UserRowmapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(1));
        user.setUserEmail(rs.getString(2));
        return user;
    }
    
}
