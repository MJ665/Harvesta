package com.courseweb.web.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.courseweb.web.Model.Admin;

public class AdminRowmapper implements RowMapper<Admin> {
    

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

        Admin admin = new Admin();
        admin.setAdminId(rs.getInt(1));
        admin.setAdminEmail(rs.getString(2));
        admin.setAdminPassword(rs.getString(3));
        return admin;
    }

}
