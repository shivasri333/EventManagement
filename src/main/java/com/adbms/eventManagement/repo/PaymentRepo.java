package com.adbms.eventManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adbms.eventManagement.entity.Payment;

public interface PaymentRepo  extends JpaRepository<Payment, Long>{

}
