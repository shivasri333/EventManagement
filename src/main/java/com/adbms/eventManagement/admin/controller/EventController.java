package com.adbms.eventManagement.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.adbms.eventManagement.dto.EventDTO;
import com.adbms.eventManagement.dto.ReviewDTO;
import com.adbms.eventManagement.dto.VenueDTO;
import com.adbms.eventManagement.entity.Event;
import com.adbms.eventManagement.entity.Image;
import com.adbms.eventManagement.entity.Payment;
import com.adbms.eventManagement.entity.Registration;
import com.adbms.eventManagement.entity.Seats;
import com.adbms.eventManagement.entity.Status;
import com.adbms.eventManagement.entity.Venue;
import com.adbms.eventManagement.service.EventService;
import com.adbms.eventManagement.service.ImageService;
import com.adbms.eventManagement.service.MyUserDetails;
import com.adbms.eventManagement.service.PaymentService;
import com.adbms.eventManagement.service.RegistrationService;
import com.adbms.eventManagement.service.ReviewService;
import com.adbms.eventManagement.service.SeatService;
import com.adbms.eventManagement.service.UserDetailService;
import com.adbms.eventManagement.service.VenueService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserDetailService userService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private PaymentService paymentService;
	

	@GetMapping("/allEvents")
	public List<Event> getAllEvents(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Event> eventsPage = eventService.getAllEvents(pageable);
		List<Event> events = eventsPage.getContent();
		modelAndView.addObject("events", events);
		return events;
	}

	@GetMapping("/approvedEvents")
	public List<Event> getAllApprovedEvents(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Event> eventsPage = eventService.getAllApprovedEvents(pageable);
		List<Event> events = eventsPage.getContent();
		modelAndView.addObject("events", events);
		return events;
	}
	
	@GetMapping("/pendingForApproval")
	public ModelAndView getAllPendingEvents(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Event> eventsPage = eventService.getAllPendingEvents(pageable);
		List<Event> events = eventsPage.getContent();
		modelAndView.addObject("events", events);
		modelAndView.setViewName("pendingForApproval");
		return modelAndView;
	}

	@GetMapping("/eventRegistration")
	public ModelAndView registration() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		if(users.getUser().getRoles().getRolename().equals("ADMIN")) {
			modelAndView.setViewName("adminEventRegistration");
		} else {
		modelAndView.setViewName("eventRegistration");
		}
		return modelAndView;
	}

	@PostMapping("/addEvent")
	public ResponseEntity<String> saveEvent(@RequestParam("event_name") String eventName,
			@RequestParam("description") String description,
			@RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam("venue") Long venueId, @RequestParam("image") MultipartFile image,
			@RequestParam("economy_seats") Long economy_seats, @RequestParam("economy_price") Long economy_price,
			@RequestParam("premium_seats") Long premium_seats, @RequestParam("premium_price") Long premium_price) {

		try {
			Image img = new Image();
			// Save the image
			if (!image.isEmpty()) {
				Image im = new Image();
				im.setImage_name(image.getOriginalFilename());
				img = imageService.saveImage(im);

				imageService.uploadImage(image);
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
			EventDTO eventDTO = new EventDTO();
			eventDTO.setEvent_name(eventName);
			eventDTO.setDescription(description);
			eventDTO.setStart_date(startDate);
			eventDTO.setEnd_date(endDate);
			Venue v = venueService.getVenueById(venueId);
			eventDTO.setVenue(v);
			eventDTO.setUser_id(users.getUser());
			eventDTO.setImage(img);
			eventDTO.setStatus(Status.PENDING_FOR_APPROVAL.toString());
			Event e =eventService.saveEvent(eventDTO);
			
			List<Seats> list = new ArrayList<>();
			Seats economy = new Seats();
			economy.setEvent(e);
			economy.setNo_of_seats(economy_seats);
			economy.setSeat_price(economy_price);
			economy.setAvailable_seats(economy_seats);
			economy.setSeat_type("Economy");
			list.add(economy);
			
			Seats premium = new Seats();
			premium.setEvent(e);
			premium.setNo_of_seats(premium_seats);
			premium.setSeat_price(premium_price);
			premium.setAvailable_seats(premium_seats);
			premium.setSeat_type("Premium");
			list.add(premium);
			
			seatService.saveAll(list);

			return new ResponseEntity<>("Event saved successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to save event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/addVenue")
	public ModelAndView displayAddVenue() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addVenue");
		return modelAndView;
		
	}
	
	@PostMapping("/addVenue")
	public ResponseEntity<String> addVenue(VenueDTO venueDto) {
		try {
			venueService.addVenue(venueDto);
		return new ResponseEntity<>("Event saved successfully", HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>("Failed to save event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@PostMapping("/addComment")
	public ResponseEntity<String> addComment(@RequestParam("comment") String comment, @RequestParam("rating") Long rating, @RequestParam("event_id") Long event_id) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
			Optional<Event> e = eventService.getEventByID(event_id);
			ReviewDTO dto = new ReviewDTO();
			dto.setComment(comment);
			dto.setRating(rating);
			dto.setEvent(e.get());
			dto.setReview_date(new Date());
			dto.setUser(users.getUser());
			reviewService.addComment(dto);
			return new ResponseEntity<>("Comment added successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to add comment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/allVenues")
	public List<Venue> getAllVenues() {
		return venueService.getAllVenues();
	}

	@GetMapping("/admin/events")
	public ModelAndView displayEvents(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Event> eventsPage = eventService.getAllEvents(pageable);
		List<Event> events = eventsPage.getContent();
		modelAndView.addObject("events", events);
		modelAndView.setViewName("allEvents");
		return modelAndView;
	}

	@GetMapping("/admin/venues")
	public ModelAndView displayVenues(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Venue> venuesPage = venueService.getAllVenues(pageable);
		List<Venue> venues = venuesPage.getContent();
		modelAndView.addObject("venues", venues);
		modelAndView.setViewName("allVenues");
		return modelAndView;
	}
	
	@GetMapping("/admin/reviews")
	public ModelAndView displayReviews() {
		ModelAndView modelAndView = new ModelAndView();
		List<ReviewDTO> reviews = reviewService.getAllReviewsForEvent();
		modelAndView.addObject("reviews", reviews);
		modelAndView.setViewName("allReviews");
		return modelAndView;
	}

	@PostMapping("/uploadImage")
	public ResponseEntity<String> UploadImage(@RequestParam("image") MultipartFile image) {

		try {
			// Save the image
			if (!image.isEmpty()) {
				Image im = new Image();
				im.setImage_name(image.getOriginalFilename());
				imageService.saveImage(im);

				imageService.uploadImage(image);
			}
			return new ResponseEntity<>("Event saved successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to upload image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/admin/deleteReview/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId) {

		try {
			reviewService.deleteReviewByID(reviewId);
			return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to delete comment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/admin/editEvent/{eventId}")
	public ModelAndView editEvent(@PathVariable(name = "eventId") Long event_id) {
		ModelAndView modelAndView = new ModelAndView();
		Optional<Event> event = eventService.getEventByID(event_id);
		modelAndView.addObject("event", event.get());
		modelAndView.addObject("imagePath", "/images/" + event.get().getImage_id().getImage_name());
		modelAndView.setViewName("editEvent");
		return modelAndView;
	}
	
	@GetMapping("/admin/editVenue/{venueId}")
	public ModelAndView editVenue(@PathVariable(name = "venueId") Long venue_id) {
		ModelAndView modelAndView = new ModelAndView();
		Venue v = venueService.getVenueById(venue_id);
		modelAndView.addObject("venue", v);
		modelAndView.setViewName("editVenue");
		return modelAndView;
	}

	@PostMapping("/admin/updateEvent")
	public ResponseEntity<String> updateEvent(EventDTO eventDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		try {
		eventService.updateEvent(eventDto,users.getUser());
		return ResponseEntity.ok("Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("/admin/updateVenue")
	public ResponseEntity<String> updateEvent(VenueDTO venueDto) {
		try {
		venueService.updateVenue(venueDto);
		return ResponseEntity.ok("Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/admin/approveEvent/{eventId}")
	public ModelAndView approveEvent(@PathVariable(name = "eventId") Long eventId) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		Optional<Event> e = eventService.getEventByID(eventId);
		Event ev = e.get();
		ev.setStatus(Status.APPROVED.toString());
		ev.setApproved_user_id(users.getUser());
		eventService.saveEventEnt(ev);
		
		modelAndView.addObject("userName", "Welcome " + users.getUsername());
			modelAndView.addObject("userName", "Welcome " + users.getUsername());
			modelAndView.setViewName("adminHomePage");
		return modelAndView;
	}

	
	@DeleteMapping("/admin/removeEvent/{eventId}")
	public ResponseEntity<String> deleteEventById(@PathVariable(name = "eventId") Long eventId) {
		try {
			eventService.deleteEvent(eventId);
			return new ResponseEntity<>("Event Deleted successfully", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Failed to Delete for event: " + ex.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/admin/removeVenue/{venueId}")
	public ResponseEntity<String> deleteVenueById(@PathVariable(name = "venueId") Long venueId,Pageable pageable) {
		try {
		venueService.deleteVenue(venueId);
		return new ResponseEntity<>("Venue Deleted successfully", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Failed to Delete for venue: " + ex.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getTickets")
	public ModelAndView getTickets(@RequestParam("eventId") Long eventID) {
		ModelAndView modelAndView = new ModelAndView();
		Optional<Event> e = eventService.getEventByID(eventID);
		modelAndView.addObject("event", e.get());
		modelAndView.addObject("imagePath", "/images/" + e.get().getImage_id().getImage_name());
		modelAndView.setViewName("ticket");
		return modelAndView;

	}

	@GetMapping("/seatTypes")
	public ResponseEntity<List<String>> getSeatTypes() {
		List<String> seatTypes = seatService.getSeatTypes();

		if (seatTypes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(seatTypes);
		}
	}

	@GetMapping("/getSeatsByEventIdandType")
	public ResponseEntity<Seats> getSeatTypes(@RequestParam String seatType, @RequestParam Long id) {
		Seats seat = seatService.getSeatsByEventIdandSeatType(seatType, id);
		if (seat.equals(null)) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(seat);
		}
	}

	@PostMapping("/buyTickets")
	public ResponseEntity<String> buyTickets(@RequestParam("eventId") Long eventID,
			@RequestParam("seatType") String seatType, @RequestParam("selectedSeats") Long selectetdSeats,
			@RequestParam("paymentType") String paymentType, @RequestParam("seatId") Long seatId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		Seats s = seatService.getSeatsById(seatId);
		Long availableSeats = s.getAvailable_seats() - selectetdSeats;
		s.setAvailable_seats(availableSeats);
		Event e = eventService.getEventByID(eventID).get();
		Registration r = new Registration();
		r.setEvent(e);
		r.setUser(users.getUser());
		r.setNo_of_seats(selectetdSeats);
		r.setRegistration_date(new Date());
		r.setSeat_type(seatType);

		try {
			seatService.updateAvailableSeats(s);

			Registration re = registrationService.registerForEvent(r);

			Payment p = new Payment();

			Long amount = s.getSeat_price() * selectetdSeats;

			p.setRegistration_id(re);
			p.setPayment_date(new Date());
			p.setPayment_method(paymentType);
			p.setStatus("Pending");
			p.setAmount(amount);

			paymentService.savePayment(p);

			return new ResponseEntity<>("Event saved successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to Register for event: " + ex.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
