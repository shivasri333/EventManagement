package com.adbms.eventManagement.mapper;

import org.springframework.stereotype.Component;

import com.adbms.eventManagement.dto.EventDTO;
import com.adbms.eventManagement.entity.Event;

@Component
public class EventMapper {
	
	public Event toEvent(EventDTO dto) {
		Event e = new Event();
		e.setEvent_name(dto.getEvent_name());
		e.setDescription(dto.getDescription());
		e.setStart_date(dto.getStart_date());
		e.setEnd_date(dto.getEnd_date());
		e.setVenue(dto.getVenue());
		e.setUser_id(dto.getUser_id());
		e.setImage_id(dto.getImage());
		e.setStatus(dto.getStatus());
		e.setApproved_user_id(dto.getApproved_user_id());
		return e;
	}

}
