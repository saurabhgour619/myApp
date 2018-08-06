package com.cg.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.BookingDAO;
import com.cg.entity.Dinning;
import com.cg.entity.Resort;

@Transactional
@Service
public class BookingServiceImpl implements BookingService {
	private static final Logger LOGGER = LogManager.getLogger("BookingServiceImpl.class");

	@Autowired
	BookingDAO bookingDAO;

	/* Resort Booking Service */
	@Override
	public Resort bookResort(Resort resort, long guestID) {
		LOGGER.debug("BookingController:Debugging bookResort method");
		LOGGER.info("BookingServiceImpl: Executing bookResort method.");
		return bookingDAO.bookResort(resort, guestID);
	}

	@Override
	public Resort updateBookResort(Resort resort, long resortBookingId) {
		LOGGER.debug("BookingController:Debugging updateBookResort method");
		LOGGER.info("BookingServiceImpl: Executing updateBookResort method.");
		return bookingDAO.updateBookResort(resort, resortBookingId);
	}

	@Override
	public Resort cancelBookResort(long resortBookingId) {
		LOGGER.debug("BookingController:Debugging cancelBookResort method");
		LOGGER.info("BookingServiceImpl: Executing cancelBookResort method.");
		return bookingDAO.cancelBookResort(resortBookingId);
	}

	/* Dinning Booking Service */
	@Override
	public Dinning bookDinning(Dinning dinning, long dinningBookingId) {
		LOGGER.debug("BookingController:Debugging bookDinning method");
		LOGGER.info("BookingServiceImpl: Executing bookDinning method.");
		return bookingDAO.bookDinning(dinning, dinningBookingId);

	}

	@Override
	public Dinning updateBookedDinning(Dinning dinning, long dinningBookingId) {
		LOGGER.debug("BookingController:Debugging updateBookedDinning method");
		LOGGER.info("BookingServiceImpl: Executing updateBookedDinning method.");
		return bookingDAO.updateBookedDinning(dinning, dinningBookingId);
	}

	@Override
	public Dinning cancelBookedDinning(long dinningBookingId) {
		LOGGER.debug("BookingController:Debugging cancelBookedDinning method");
		LOGGER.info("BookingServiceImpl: Executing cancelBookResort method.");
		return bookingDAO.cancelBookedDinning(dinningBookingId);
	}

	/* View Booking Service */
	@Override
	public List<Resort> getAllResortDetails(long guest_id) {
		LOGGER.debug("BookingController:Debugging getAllResortDetails method");
		LOGGER.info("BookingServiceImpl: Executing getAllResortDetails method.");
		return bookingDAO.getAllResortDetails(guest_id);
	}

	@Override
	public List<Dinning> getAllDiningDetails(long guest_id) {
		LOGGER.debug("BookingController:Debugging getAllDiningDetails method");
		LOGGER.info("BookingServiceImpl: Executing getAllDiningDetails method.");
		return bookingDAO.getAllDinningDetails(guest_id);
	}

}
