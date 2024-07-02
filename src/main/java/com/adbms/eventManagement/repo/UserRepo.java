package com.adbms.eventManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adbms.eventManagement.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	@Query(value = "select u from User u where u.username = :username")
	public User findByUserName(String username);

}
