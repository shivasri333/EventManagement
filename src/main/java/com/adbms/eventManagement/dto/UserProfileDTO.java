package com.adbms.eventManagement.dto;

import java.util.Date;

public class UserProfileDTO implements UserProfileProjection{
	
	private Date registration_date;
	
	private Long no_of_seats;
	
	private String seat_type;
	
	private Long event_id;
	
	private String event_name;
	
	private Date start_date;
	
	private Date end_date;
	
	private String venue_name;
	
	private Long amount;
	
	private String payment_method;
	
	private String status;

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public Long getNo_of_seats() {
		return no_of_seats;
	}

	public void setNo_of_seats(Long no_of_seats) {
		this.no_of_seats = no_of_seats;
	}

	public String getSeat_type() {
		return seat_type;
	}

	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	

	public Long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getVenue_name() {
		return venue_name;
	}

	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserProfileDTO( Date registration_date, Long no_of_seats,
			String seat_type, Long event_id, String event_name, Date start_date, Date end_date, String venue_name, Long amount,
			String payment_method, String status) {
		super();
		this.registration_date = registration_date;
		this.no_of_seats = no_of_seats;
		this.seat_type = seat_type;
		this.event_id = event_id;
		this.event_name = event_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.venue_name = venue_name;
		this.amount = amount;
		this.payment_method = payment_method;
		this.status = status;
	}

	
}
