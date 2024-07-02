package com.adbms.eventManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.adbms.eventManagement.entity.Seats;

public interface SeatsRepo  extends JpaRepository<Seats, Long>{

	@Query("select s from Seats s where s.event.event_id =:id and s.seat_type=:type ")
	Seats getSeatsByEventIdandSeatType(Long id, String type);

	@Query("select distinct s.seat_type from Seats s ")
	List<String> getSeatTypes();

	@Modifying
	@Query("DELETE from Seats s where s.event.event_id =:eventId ")
	void deleteAllByEventId(Long eventId);

}
