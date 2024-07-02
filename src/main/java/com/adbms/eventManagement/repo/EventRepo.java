package com.adbms.eventManagement.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adbms.eventManagement.entity.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long>   {
	
	@Query("select e from Event e where e.status='APPROVED' ")
	Page<Event> findAllApprovedEvents(Pageable pageable);

	@Query("select e from Event e where e.approved_user_id.userid = :userid ")
	List<Event> findEventsByApproveUserId(Long userid);

	@Query("select e from Event e where e.status='PENDING_FOR_APPROVAL' ")
	Page<Event> findAllPendingEvents(Pageable pageable);

}
