package com.courseweb.web.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.courseweb.web.Model.Crops;

public class CropsRowmapper implements RowMapper<Crops> {

    @Override
    public Crops mapRow(ResultSet rs, int rowNum) throws SQLException {
        Crops crop=new Crops();
        crop.setCropId(rs.getInt(1));
        crop.setCropName(rs.getString(2));
        crop.setCropPrice(rs.getInt(3));
        return crop;
    }
    
}
