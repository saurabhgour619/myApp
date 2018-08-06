package com.cg.entity;

import java.util.Date;

public class Dinning {
	private long dReservationNumber;
	private String dinningType;
	private Date arrivalDate;
	private int noOfPeople;
	private String status;
	private Date createdDate;
	private Date updatedDate;

	private long guestID;

	public long getdReservationNumber() {
		return dReservationNumber;
	}

	public void setdReservationNumber(long dReservationNumber) {
		this.dReservationNumber = dReservationNumber;
	}

	public String getDinningType() {
		return dinningType;
	}

	public void setDinningType(String dinningType) {
		this.dinningType = dinningType;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getGuestID() {
		return guestID;
	}

	public void setGuestID(long guestID) {
		this.guestID = guestID;
	}

}
