package com.adbms.eventManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adbms.eventManagement.dto.VenueDTO;

@Entity
@Table(name = "venue",schema="event_management")
public class Venue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venue_id")
	private Long venue_id;
	
	@Column(name = "venue_name")
	private String venue_name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "max_capacity")
	private Long max_capacity;
	
	@Column(name = "contact_person")
	private String contact_person;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	public Venue() {
		
	}

	public Venue(VenueDTO venueDto) {
		this.venue_name = venueDto.getVenue_name();
		this.address = venueDto.getAddress();
		this.max_capacity = venueDto.getMax_capacity();
		this.contact_person = venueDto.getContact_person();
		this.email = venueDto.getEmail();
		this.phone = venueDto.getPhone();
	}

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

	
	
}
