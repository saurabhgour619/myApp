package com.cg.bootjdbctemplate;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.entity.Guest;
import com.cg.service.GuestServiceImpl;

public class GuestServiceTest extends BootJdbctemplateApplicationTests {

	@Autowired
	GuestServiceImpl guestService;

	@Test
	public void testRegisterGuest() {
		Guest guest = new Guest();
		guest.setEmail("sg@cg.com");
		guest.setFirstName("Sourabh");
		guest.setLastName("Gour");
		guest.setAddress("Nagpur");
		guest.setPhone("+918983276345");
		guest.setPassword("sourabh");

		Guest guestTestObj = guestService.registerGuest(guest);
		assertNotNull(guestTestObj);
	}

	@Test
	public void testValidateGuest() {
		String em = "sg@cg.com";
		String pass = "sourabh";

		Guest guestTestObj = guestService.validateGuest(em, pass);
		assertNotNull(guestTestObj);

	}
}
