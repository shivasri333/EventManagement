package com.adbms.eventManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.entity.Seats;
import com.adbms.eventManagement.repo.SeatsRepo;

@Service
public class SeatService {

	@Autowired
	SeatsRepo seatRepository;
	
	public Seats getSeatsByEventIdandSeatType(String type, Long id) {
		return seatRepository.getSeatsByEventIdandSeatType(id, type);
	}
	
	public List<String> getSeatTypes(){
		return seatRepository.getSeatTypes();
	}
	
	public void updateAvailableSeats(Seats s) {
		seatRepository.save(s);
	}
	
	public Seats getSeatsById(Long id) {
		return seatRepository.getById(id);
	}

	public void saveAll(List<Seats> list) {
		seatRepository.saveAll(list);
	}
	
	@Transactional
	public void deleteSeatsByEventID(Long eventId) {
		seatRepository.deleteAllByEventId(eventId);
	}
}
