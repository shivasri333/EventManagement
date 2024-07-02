package com.adbms.eventManagement.dto;

import java.util.Date;

public interface UserProfileProjection {

	Date getRegistration_date();

	Long getNo_of_seats();

	String getSeat_type();

	Long getEvent_id();
	
	String getEvent_name();

	Date getStart_date();

	Date getEnd_date();

	String getVenue_name();

	Long getAmount();

	String getPayment_method();

	String getStatus();

}
