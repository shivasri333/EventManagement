package com.adbms.eventManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adbms.eventManagement.entity.Image;

@Repository
public interface UploadImageRepo extends JpaRepository<Image, Long> {

}
