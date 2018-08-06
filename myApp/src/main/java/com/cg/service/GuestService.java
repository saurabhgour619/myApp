package com.cg.service;

import com.cg.entity.Guest;

public interface GuestService {

	public Guest registerGuest(Guest guest);

	public Guest validateGuest(String em, String pass);

	public Guest updateGuest(Guest guest, long guestID);

}
