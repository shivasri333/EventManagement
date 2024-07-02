package com.adbms.eventManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.entity.Payment;
import com.adbms.eventManagement.repo.PaymentRepo;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepo paymentRepo;
	
	
	public Payment savePayment(Payment p) {
		return paymentRepo.save(p);
	}

}
