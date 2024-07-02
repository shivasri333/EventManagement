package com.adbms.eventManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adbms.eventManagement.dto.UserProfileDTO;
import com.adbms.eventManagement.dto.UserProfileProjection;
import com.adbms.eventManagement.entity.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, Long> {

	@Query(nativeQuery = true, value = "select r.registration_date, r.no_of_seats, r.seat_type,e.event_id, e.event_name, e.start_date,e.end_date, v.venue_name, i.image_name, p.amount, p.payment_method, p.status from event_management.\"user\" u "
			+ "left join event_management.registrations r on r.user_id = u.user_id "
			+ "left join event_management.\"event\" e on r.event_id = e.event_id "
			+ "left join event_management.venue v on v.venue_id = e.venue "
			+ "left join event_management.image i on i.image_id = e.image_id "
			+ "left join event_management.payment p on p.registration_id = r.registration_id " 
			+ "where u.user_id=:id")
	public List<UserProfileProjection> getUserProfile(Long id);

}
