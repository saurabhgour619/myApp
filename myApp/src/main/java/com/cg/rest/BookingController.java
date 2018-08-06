package com.cg.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;
import com.cg.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	private static final Logger LOGGER = LogManager.getLogger("BookingController.class");

	@Autowired
	private BookingService bookingService;

	@PostMapping("/resort/{guestID}")
	public ResponseEntity<Resort> bookResort(@RequestBody Resort resort, @PathVariable long guestID) {
		LOGGER.debug("BookingController:Debugging bookResort method");
		LOGGER.info("BookingController:	Executing Resort Booking for a Guest.");
		Resort bookedResort = bookingService.bookResort(resort, guestID);
		if (bookedResort != null) {
			LOGGER.info("Resort Booked for a Guest and bookedResort Entity returned.");
			return new ResponseEntity<Resort>(bookedResort, HttpStatus.CREATED);
		} else {
			LOGGER.warn("Resort booking failed for a Guest.");
			return new ResponseEntity<Resort>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/resort/{resortBookingId}")
	public ResponseEntity<Resort> updateBookResort(@RequestBody Resort resort, @PathVariable long resortBookingId) {
		LOGGER.debug("BookingController:Debugging updateBookResort method");
		LOGGER.info("BookingController:	Executing updateBookResort for a Guest.");
		Resort r = bookingService.updateBookResort(resort, resortBookingId);
		if (r != null) {
			LOGGER.info("Booked Resort for a Guest is updated.");
			return new ResponseEntity<Resort>(r, HttpStatus.CREATED);
		} else {
			LOGGER.warn("Booked Resort for a Guest is not updated.");
			return new ResponseEntity<Resort>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/cancel/resort/{resortBookingId}")
	public ResponseEntity<Resort> cancelBookResort(@PathVariable long resortBookingId) {
		LOGGER.debug("BookingController:Debugging cancelBookResort method");
		LOGGER.info("BookingController:	Executing cancelBookResort for a Guest.");
		Resort r = bookingService.cancelBookResort(resortBookingId);
		if (r != null) {
			LOGGER.info("BookingController:	Booked Resort for a Guest is canceled.");
			return new ResponseEntity<Resort>(r, HttpStatus.OK);
		} else {
			LOGGER.warn("BookingController:	Booked Resort for a Guest is not canceled.");
			return new ResponseEntity<Resort>(HttpStatus.BAD_REQUEST);
		}
	}

	/* Dinning */

	@PostMapping("/dinning/{guestID}")
	public ResponseEntity<Dinning> bookDinning(@RequestBody Dinning dinning, @PathVariable long guestID) {
		LOGGER.debug("BookingController:Debugging bookDinning method");
		LOGGER.info("Executing Dinning Booking for a Guest.");
		Dinning bookDinning = bookingService.bookDinning(dinning, guestID);
		if (bookDinning != null) {
			LOGGER.info("BookingController:	Dinning Booked for a Guest.");
			return new ResponseEntity<Dinning>(bookDinning, HttpStatus.CREATED);
		} else {
			LOGGER.warn("BookingController:	Dinning not booked for a Guest.");
			return new ResponseEntity<Dinning>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/dinning/{dinningBookingId}")
	public ResponseEntity<Dinning> updateBookedDinning(@RequestBody Dinning dinning,
			@PathVariable long dinningBookingId) {
		LOGGER.debug("BookingController:Debugging updateBookedDinning method");
		LOGGER.info("BookingController:Updating Dinning Booking for a Guest.");
		Dinning d = bookingService.updateBookedDinning(dinning, dinningBookingId);
		if (d != null) {
			LOGGER.info("BookingController:Booked Resort for a Guest is updated.");
			return new ResponseEntity<Dinning>(d, HttpStatus.CREATED);
		} else {
			LOGGER.warn("BookingController:Booked Dinning for a Guest is not updated.");
			return new ResponseEntity<Dinning>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/cancel/dinning/{dinningBookingId}")
	public ResponseEntity<Dinning> cancelBookedDinning(@PathVariable long dinningBookingId) {
		LOGGER.debug("BookingController:Debugging cancelBookedDinning method");
		LOGGER.info("BookingController:Cancelling Dinning Booking for a Guest.");
		Dinning d = bookingService.cancelBookedDinning(dinningBookingId);
		if (d != null) {
			LOGGER.info("BookingController:Cancelled Dinning Booking for a Guest.");
			return new ResponseEntity<Dinning>(d, HttpStatus.OK);
		} else {
			LOGGER.error("BookingController:Booked Dinning for a Guest is not cancelled.");
			return new ResponseEntity<Dinning>(HttpStatus.BAD_REQUEST);
		}
	}
}
