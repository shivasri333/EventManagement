package com.adbms.eventManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adbms.eventManagement.entity.Venue;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Long>  {

}
