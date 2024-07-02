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
@Table(name = "event",schema="event_management")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long event_id;
	
	@Column(name = "event_name")
	private String event_name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	private Date start_date;
	
	@Column(name = "end_date")
	private Date end_date;
	
	@OneToOne
	@JoinColumn(name = "venue")
	private Venue venue;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne
	@JoinColumn(name = "user_registered")
	private User user_id;
	
	@OneToOne
	@JoinColumn(name = "image_id")
	private Image image_id;
	
	@OneToOne
	@JoinColumn(name = "approved_user")
	private User approved_user_id;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Image getImage_id() {
		return image_id;
	}

	public void setImage_id(Image image_id) {
		this.image_id = image_id;
	}

	public User getApproved_user_id() {
		return approved_user_id;
	}

	public void setApproved_user_id(User approved_user_id) {
		this.approved_user_id = approved_user_id;
	}
	
	

}
