package com.adbms.eventManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.dto.VenueDTO;
import com.adbms.eventManagement.entity.Venue;
import com.adbms.eventManagement.repo.VenueRepo;

@Service
public class VenueService {

	@Autowired
	private VenueRepo venueRepo;
	
	public List<Venue> getAllVenues(){
		return venueRepo.findAll();
	}

	public Venue getVenueById(Long venueId) {
		return venueRepo.getById(venueId);
	}

	public Page<Venue> getAllVenues(Pageable pageable) {
		return venueRepo.findAll(pageable);
	}

	public void deleteVenue(Long venueId) {
		venueRepo.deleteById(venueId);
	}

	public void updateVenue(VenueDTO venueDto) {
		Venue v = getVenueById(venueDto.getVenue_id());
		v.setAddress(venueDto.getAddress());
		v.setContact_person(venueDto.getContact_person());
		v.setEmail(venueDto.getEmail());
		v.setMax_capacity(venueDto.getMax_capacity());
		v.setPhone(venueDto.getPhone());
		venueRepo.save(v);
	}

	public void addVenue(VenueDTO venueDto) {
		Venue v = new Venue(venueDto);
		venueRepo.save(v);
		
	}
}
