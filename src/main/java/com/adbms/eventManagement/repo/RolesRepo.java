package com.adbms.eventManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adbms.eventManagement.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long>{

	@Query(value = "select r from Roles r where r.rolename = :role")
	Roles findRoleByName(String role);

}
