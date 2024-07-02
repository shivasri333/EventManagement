package com.adbms.eventManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registrations",schema="event_management")
public class Registration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_id")
	private Long registration_id;
	
	@OneToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "no_of_seats")
	private Long no_of_seats;
	
	@Column(name = "registration_date")
	private Date registration_date;
	
	@Column(name = "seat_type")
	private String seat_type;

	public Long getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(Long registration_id) {
		this.registration_id = registration_id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getNo_of_seats() {
		return no_of_seats;
	}

	public void setNo_of_seats(Long no_of_seats) {
		this.no_of_seats = no_of_seats;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getSeat_type() {
		return seat_type;
	}

	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	
	

}
