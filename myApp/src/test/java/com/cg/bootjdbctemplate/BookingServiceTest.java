package com.cg.bootjdbctemplate;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.entity.Guest;
import com.cg.entity.Resort;
import com.cg.service.BookingServiceImpl;

public class BookingServiceTest extends BootJdbctemplateApplicationTests {

	@Autowired
	BookingServiceImpl bookingService;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();

	@Test
	public void testBookResort() {
		Resort resort = new Resort();
		resort.setRoomType("testroom");
		resort.setNoOfPeople(1);
		resort.setArrivalDate(date);
		resort.setDepartureDate(date);

		Guest guest = new Guest();
		guest.setGuestID(1);
		long guestID = guest.getGuestID();

		Resort bookedResortTestObj = bookingService.bookResort(resort, guestID);
		assertNotNull(bookedResortTestObj);

	}

}
