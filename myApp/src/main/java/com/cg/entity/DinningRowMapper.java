package com.cg.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DinningRowMapper implements RowMapper<Dinning> {
	@Override
	public Dinning mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dinning dinning = new Dinning();
		dinning.setdReservationNumber(rs.getLong("d_reservation_number"));
		dinning.setGuestID(rs.getLong("guest_id_fk"));
		dinning.setDinningType(rs.getString("dinning_type"));
		dinning.setArrivalDate(rs.getDate("arrival_date"));
		dinning.setNoOfPeople(rs.getInt("no_of_people"));
		dinning.setStatus(rs.getString("status"));
		dinning.setCreatedDate(rs.getDate("created_date"));
		dinning.setUpdatedDate(rs.getDate("updated_date"));
		return dinning;
	}
}