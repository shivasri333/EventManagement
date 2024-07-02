package com.adbms.eventManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.dto.UserProfileDTO;
import com.adbms.eventManagement.dto.UserProfileProjection;
import com.adbms.eventManagement.entity.Registration;
import com.adbms.eventManagement.repo.RegistrationRepo;

@Service
public class RegistrationService {

	@Autowired
	RegistrationRepo registrationRepo;

	public Registration registerForEvent(Registration r) {
		return registrationRepo.save(r);
	}

	public List<UserProfileDTO> findUserProfileByUserId(Long userId) {
		List<UserProfileProjection> pro = registrationRepo.getUserProfile(userId);
		List<UserProfileDTO> dtos = new ArrayList<>();
		for (UserProfileProjection p : pro) {
			UserProfileDTO dto = new UserProfileDTO(p.getRegistration_date(), p.getNo_of_seats(), p.getSeat_type(),p.getEvent_id(), p.getEvent_name(),
					p.getStart_date(), p.getEnd_date(), p.getVenue_name(), p.getAmount(), p.getPayment_method(),
					p.getStatus());
			dtos.add(dto);
		}
		return dtos;
	}

}
