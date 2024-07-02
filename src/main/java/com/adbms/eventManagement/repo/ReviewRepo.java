package com.adbms.eventManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adbms.eventManagement.entity.Reviews;

@Repository
public interface ReviewRepo extends JpaRepository<Reviews, Long>{

}
