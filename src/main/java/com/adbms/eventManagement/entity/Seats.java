package com.adbms.eventManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seats",schema="event_management")
public class Seats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seat_id")
	private Long seat_id;
	
	@OneToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	@Column(name = "no_of_seats")
	private Long no_of_seats;
	
	@Column(name = "seat_price")
	private Long seat_price;
	
	@Column(name = "available_seats")
	private Long available_seats;
	
	@Column(name = "seat_type")
	private String seat_type;

	public Long getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(Long seat_id) {
		this.seat_id = seat_id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Long getNo_of_seats() {
		return no_of_seats;
	}

	public void setNo_of_seats(Long no_of_seats) {
		this.no_of_seats = no_of_seats;
	}

	public Long getSeat_price() {
		return seat_price;
	}

	public void setSeat_price(Long seat_price) {
		this.seat_price = seat_price;
	}

	public Long getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(Long available_seats) {
		this.available_seats = available_seats;
	}

	public String getSeat_type() {
		return seat_type;
	}

	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}
	
}
