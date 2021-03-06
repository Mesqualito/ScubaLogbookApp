package com.andreahowes.dive_db.data.dives;

import com.andreahowes.dive_db.logic.dive.Dive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class DiveRowMapper implements RowMapper<Dive> {
    @Override
    public Dive mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dive dive = new Dive();
        dive.setId(rs.getInt("d_id"));
        dive.setUser(rs.getString("d_user"));
        dive.setDate(rs.getDate("d_date").toLocalDate());
        dive.setLocation(rs.getString("d_location"));
        dive.setDurationInMinutes(rs.getDouble("d_duration_in_minutes"));
        dive.setMaxDepthInMeters(rs.getDouble("d_max_depth_in_meters"));
        dive.setWaterConditions(rs.getString("d_water_conditions"));
        dive.setSafetyStop(rs.getBoolean("d_safety_stop"));
        return dive;
    }
}

