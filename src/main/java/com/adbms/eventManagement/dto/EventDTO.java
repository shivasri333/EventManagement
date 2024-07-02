package com.adbms.eventManagement.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.adbms.eventManagement.entity.Image;
import com.adbms.eventManagement.entity.User;
import com.adbms.eventManagement.entity.Venue;

public class EventDTO {

	private Long event_id;

	private String event_name;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start_date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;

	private Venue venue;

	private String status;

	private User user_id;
	
	private Image image;
	
	private User approved_user_id;

	public EventDTO() {
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public User getApproved_user_id() {
		return approved_user_id;
	}

	public void setApproved_user_id(User approved_user_id) {
		this.approved_user_id = approved_user_id;
	}

}
