package com.cg.dao;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.entity.Dinning;
import com.cg.entity.DinningRowMapper;
import com.cg.entity.Resort;
import com.cg.entity.ResortRowMapper;

@Repository
public class BookingDAOImpl implements BookingDAO {
	private static final Logger LOGGER = LogManager.getLogger("BookingDAOImpl.class");

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Date date = new Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	private static final String SQL_BOOK_RESORT_BY_ID = "INSERT INTO resort_reservation(guest_id_fk, room_type, arrival_date, departure_date, no_of_people, status, created_date, updated_date)"
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID = "UPDATE resort_reservation SET room_type = ?, arrival_date=?, departure_date=?, "
			+ "no_of_people =?, updated_date=? where r_reservation_number = ?";

	private static final String SQL_BOOK_DINNING_BY_ID = "INSERT INTO dinning_reservation(guest_id_fk, dinning_type, arrival_date, no_of_people, status, created_date, updated_date)"
			+ "values (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID = "UPDATE dinning_reservation SET dinning_type = ?, arrival_date=?,"
			+ "no_of_people =?, updated_date=? where d_reservation_number = ?";

	@Override
	public Resort bookResort(Resort resort, long guestID) {
		LOGGER.info("BookingDAOImpl:Executing bookResort method.");
		try {
			resort.setGuestID(guestID);
			resort.setStatus("booked");
			resort.setCreatedDate(date);
			resort.setUpdatedDate(date);

			Object[] params = { resort.getGuestID(), resort.getRoomType(), resort.getArrivalDate(),
					resort.getDepartureDate(), resort.getNoOfPeople(), resort.getStatus(), resort.getCreatedDate(),
					resort.getUpdatedDate() };

			jdbcTemplate.update(SQL_BOOK_RESORT_BY_ID, params);
			LOGGER.info("BookingDAOImpl:Resort Booked.");
		} catch (Exception e) {
			LOGGER.warn("BookingDAOImpl:Resort booking failed.");
			return null;
		}
		return getResortbyGuestID(guestID);
	}

	@Override
	public Resort updateBookResort(Resort resort, long resortBookingId) {
		LOGGER.info("BookingDAOImpl:Executing updateBookResort.");
		try {
			resort.setrReservationNumber(resortBookingId);
			resort.setUpdatedDate(date);
			Object[] params = { resort.getRoomType(), resort.getArrivalDate(), resort.getDepartureDate(),
					resort.getNoOfPeople(), resort.getUpdatedDate(), resort.getrReservationNumber() };

			jdbcTemplate.update(SQL_UPDATE_BOOKED_RESORT_BY_REGISTER_ID, params);
			LOGGER.info("BookingDAOImpl:Resort Updated.");
		} catch (Exception e) {
			LOGGER.warn("BookingDAOImpl:Resort Update failed.");
			return null;
		}
		return getResortByResortBookingID(resortBookingId);
	}

	@Override
	public Resort cancelBookResort(long resortBookingId) {
		LOGGER.info("BookingDAOImpl:Executing cancelBookResort");
		String status = "cancelled";
		String query = "UPDATE resort_reservation SET status=? where r_reservation_number=" + resortBookingId + "";

		try {
			jdbcTemplate.update(query, status);
			LOGGER.info("BookingDAOImpl:Resort Booking Cancelled ");
		} catch (Exception e) {
			LOGGER.warn("BookingDAOImpl:Cancelling Resort failed.");
			return null;
		}
		return getResortByResortBookingID(resortBookingId);
	}

	@Override
	public Dinning bookDinning(Dinning dinning, long guestID) {
		LOGGER.info("BookingDAOImpl:Executing bookDinning method.");
		try {
			dinning.setGuestID(guestID);
			dinning.setStatus("booked");
			dinning.setCreatedDate(date);
			dinning.setUpdatedDate(date);

			Object[] params = { dinning.getGuestID(), dinning.getDinningType(), dinning.getArrivalDate(),
					dinning.getNoOfPeople(), dinning.getStatus(), dinning.getCreatedDate(), dinning.getUpdatedDate() };

			jdbcTemplate.update(SQL_BOOK_DINNING_BY_ID, params);
			LOGGER.info("BookingDAOImpl:Dining Booked.");
		} catch (Exception e) {
			LOGGER.warn("BookingDAOImpl:Dining Booking failed.");
			return null;
		}
		return getDinningbyGuestID(guestID);
	}

	@Override
	public Dinning updateBookedDinning(Dinning dinning, long dinningBookingId) {

		LOGGER.info("BookingDAOImpl:Executing updateBookedDinning.");
		dinning.setdReservationNumber(dinningBookingId);
		dinning.setUpdatedDate(date);

		Object[] params = { dinning.getDinningType(), dinning.getArrivalDate(), dinning.getNoOfPeople(),
				dinning.getUpdatedDate(), dinning.getdReservationNumber() };

		jdbcTemplate.update(SQL_UPDATE_BOOKED_DINNING_BY_REGISTER_ID, params);
		LOGGER.info("BookingDAOImpl:Dining Updated.");
		return getDinningByDinningBookingID(dinningBookingId);
	}

	@Override
	public Dinning cancelBookedDinning(long dinningBookingId) {
		LOGGER.info("BookingDAOImpl:	Executing cancelBookedDinning");
		String status = "cancelled";
		String query = "UPDATE dinning_reservation SET status=? where d_reservation_number=" + dinningBookingId + "";
		try {
			jdbcTemplate.update(query, status);
			LOGGER.info("BookingDAOImpl:Resort Cancelled.");
		} catch (Exception e) {
			LOGGER.warn("BookingDAOImpl:Resort Not Cancelled.");
			return null;
		}
		return getDinningByDinningBookingID(dinningBookingId);
	}

	// helper method
	public Resort getResortByResortBookingID(long resortBookingId) {
		String query = "select * from resort_reservation where r_reservation_number=" + resortBookingId + "";
		Resort resort = jdbcTemplate.queryForObject(query, new ResortRowMapper());
		LOGGER.info("BookingDAOImpl:Resort Entity Returned.");
		return resort;
	}

	public Resort getResortbyGuestID(long guestID) {
		String query = "select * from resort_reservation where guest_id=" + guestID + "";
		Resort resort = jdbcTemplate.queryForObject(query, new ResortRowMapper());
		LOGGER.info("BookingDAOImpl:Resort Entity Returned.");
		return resort;
	}

	public Dinning getDinningByDinningBookingID(long dinningBookingId) {
		String query = "select * from dinning_reservation where d_reservation_number=" + dinningBookingId + "";
		Dinning dinning = jdbcTemplate.queryForObject(query, new DinningRowMapper());
		LOGGER.info("BookingDAOImpl:Dinning Entity Returned.");
		return dinning;
	}

	public Dinning getDinningbyGuestID(long guestID) {
		String query = "select * from dinning_reservation where guest_id=" + guestID + "";
		Dinning dinning = jdbcTemplate.queryForObject(query, new DinningRowMapper());
		LOGGER.info("BookingDAOImpl:Dinning Entity Returned.");
		return dinning;
	}

	@Override
	public List<Resort> getAllResortDetails(long guestID) {
		LOGGER.info("BookingDAOImpl:Executing getAllResortDetails method.");
		String query = "SELECT * FROM resort_reservation WHERE guest_id_fk=" + guestID + "";
		List<Resort> resort = jdbcTemplate.query(query, new ResortRowMapper());
		LOGGER.info("BookingDAOImpl:Resort Entity Returned.");
		return resort;
	}

	@Override
	public List<Dinning> getAllDinningDetails(long guestID) {
		LOGGER.info("BookingDAOImpl:Executing getAllDinningDetails method.");
		String query = "SELECT * FROM dinning_reservation WHERE guest_id_fk=" + guestID + "";
		List<Dinning> dinning = jdbcTemplate.query(query, new DinningRowMapper());
		LOGGER.info("BookingDAOImpl:Dining Entity Returned.");
		return dinning;
	}
}