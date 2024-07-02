package com.adbms.eventManagement.dto;

public class VenueDTO {
	
private Long venue_id;
	
	private String venue_name;
	
	private String address;
	
	private Long max_capacity;
	
	private String contact_person;
	
	private String email;
	
	private String phone;

	public Long getVenue_id() {
		return venue_id;
	}

	public void setVenue_id(Long venue_id) {
		this.venue_id = venue_id;
	}

	public String getVenue_name() {
		return venue_name;
	}

	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(Long max_capacity) {
		this.max_capacity = max_capacity;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public VenueDTO() {
	}

	
	
}
