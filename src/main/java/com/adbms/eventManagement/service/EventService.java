package com.adbms.eventManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.dto.EventDTO;
import com.adbms.eventManagement.entity.Event;
import com.adbms.eventManagement.entity.User;
import com.adbms.eventManagement.mapper.EventMapper;
import com.adbms.eventManagement.repo.EventRepo;

@Service
public class EventService {

	@Autowired
	private EventRepo eventRepo;

	@Autowired
	private EventMapper eventMapper;
	
	@Autowired
	SeatService seatService;

	public Page<Event> getAllEvents(Pageable pageable) {
		return eventRepo.findAll(pageable);
	}

	public Page<Event> getAllApprovedEvents(Pageable pageable) {
		return eventRepo.findAllApprovedEvents(pageable);
	}
	
	public Event saveEvent(EventDTO dto) {
		Event e = eventMapper.toEvent(dto);
		return eventRepo.save(e);
	}

	public Optional<Event> getEventByID(Long id) {
		return eventRepo.findById(id);
	}

	public void deleteEvent(Long event_id) {
		seatService.deleteSeatsByEventID(event_id);
		eventRepo.deleteById(event_id);
	}

	public List<Event> getUserAssociatedEvents(Long userid) {
		return eventRepo.findEventsByApproveUserId(userid);
	}

	public void updateEvent(EventDTO eventDto, User approvedUser) {
		Event e = eventRepo.findById(eventDto.getEvent_id()).get();
		e.setVenue(eventDto.getVenue());
		e.setApproved_user_id(approvedUser);
		e.setDescription(eventDto.getDescription());
		e.setEnd_date(eventDto.getEnd_date());
		e.setStart_date(eventDto.getStart_date());
		e.setStatus(eventDto.getStatus());
		eventRepo.save(e);
	}

	public Page<Event> getAllPendingEvents(Pageable pageable) {
		return eventRepo.findAllPendingEvents(pageable);
	}

	public void saveEventEnt(Event ev) {
		 eventRepo.save(ev);
	}

}
