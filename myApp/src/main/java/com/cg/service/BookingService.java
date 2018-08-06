package com.cg.service;

import java.util.List;

import com.cg.entity.Dinning;
import com.cg.entity.Resort;

public interface BookingService {

	public Resort bookResort(Resort resort, long guestID);

	public Resort updateBookResort(Resort resort, long resortBookingId);

	public Resort cancelBookResort(long resortBookingId);

	public Dinning bookDinning(Dinning dinning, long guestID);

	public Dinning updateBookedDinning(Dinning dinning, long dinningBookingId);

	public Dinning cancelBookedDinning(long dinningBookingId);

	public List<Resort> getAllResortDetails(long guestID);

	public List<Dinning> getAllDiningDetails(long guestID);

}
