package com.adbms.eventManagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.dto.ReviewDTO;
import com.adbms.eventManagement.entity.Event;
import com.adbms.eventManagement.entity.Reviews;
import com.adbms.eventManagement.repo.ReviewRepo;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	public List<Reviews> findAllReviews(){
		return reviewRepo.findAll();
	}
	
	public List<ReviewDTO> getAllReviewsForEvent(){
		List<Reviews> reviews = findAllReviews();
//		Map<String, List<ReviewDTO>> eventReviewsMap = new HashMap<>();
		List<ReviewDTO> dtos = new ArrayList<>();
        for (Reviews review : reviews) {
//            String key = review.getEvent().getEvent_name();
//            if (!eventReviewsMap.containsKey(key)) {
//                eventReviewsMap.put(key, new ArrayList<>());
//            }
            ReviewDTO dto = new ReviewDTO();
            dto.setReview_id(review.getReview_id());
            dto.setComment(review.getComment());
            dto.setEvent(review.getEvent());
            dto.setRating(review.getRating());
            dto.setReview_date(review.getReview_date());
            dto.setUser(review.getUser());
            dtos.add(dto);
//            eventReviewsMap.get(key).add(dto);
            
        }
		return dtos;
		
	}
	
	public void addComment(ReviewDTO reviewDto) {
		Reviews r = new Reviews();
		r.setComment(reviewDto.getComment());
		r.setEvent(reviewDto.getEvent());
		r.setRating(reviewDto.getRating());
		r.setReview_date(reviewDto.getReview_date());
		r.setUser(reviewDto.getUser());
		reviewRepo.save(r);
	}

	public void deleteReviewByID(Long reviewId) {
		reviewRepo.deleteById(reviewId);
	}

}
