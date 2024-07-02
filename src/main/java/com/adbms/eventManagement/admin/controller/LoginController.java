package com.adbms.eventManagement.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adbms.eventManagement.dto.UserProfileDTO;
import com.adbms.eventManagement.entity.Event;
import com.adbms.eventManagement.entity.User;
import com.adbms.eventManagement.service.EventService;
import com.adbms.eventManagement.service.MyUserDetails;
import com.adbms.eventManagement.service.RegistrationService;
import com.adbms.eventManagement.service.UserDetailService;

@Controller
public class LoginController {

	@Autowired
	private UserDetailService userService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private RegistrationService registrationService;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping({ "/"})//, "/login"
	public ModelAndView Home() {
		ModelAndView modelAndView = new ModelAndView();
		String message = "Login if Existing User";
		modelAndView.addObject("message", message);
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/login")//, 
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		String message = "Login if Existing User";
		modelAndView.addObject("message", message);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("/eventAdmin")//, 
	public ModelAndView Default() {
		ModelAndView modelAndView = new ModelAndView();
		String message = "Login if Existing User";
		modelAndView.addObject("message", message);
		modelAndView.setViewName("adminHome");
		return modelAndView;
	}
	
	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User users = new User();
		modelAndView.addObject("user", users);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@GetMapping("/homePage")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		modelAndView.addObject("userName", "Welcome " + users.getUsername());
			modelAndView.addObject("userName", "Welcome " + users.getUsername());
			modelAndView.setViewName("homePage");
		return modelAndView;
	}
	
	@GetMapping("/adminHomePage")
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails users = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		modelAndView.addObject("userName", "Welcome " + users.getUsername());
			modelAndView.addObject("userName", "Welcome " + users.getUsername());
			modelAndView.setViewName("adminHomePage");

		return modelAndView;
	}

	@PostMapping("/registration")
	public ModelAndView createNewUser(String username, String password, String roleName, String firstName,
			String lastName, String phone, String email, String licenseNumber, RedirectAttributes redirectAttributes) {
		String message = "";
		String pass = bCryptPasswordEncoder.encode(password);
		User users = new User();
		users.setUsername(username);
		users.setPassword(pass);

		ModelAndView modelAndView = new ModelAndView();
		User usersExists = userService.findByUserName(users.getUsername());
		if (usersExists != null) {
			message = "This User already exits";
			modelAndView.setViewName("registration");
		} else {
			roleName = "USER";
			userService.saveUser(users.getUsername(), users.getPassword(), roleName, phone, email);
			modelAndView.addObject("successMessage", "Users has been registered successfully");
			modelAndView.addObject("user", new User());
			message = "User has been registered successfully";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("login");
		}
		redirectAttributes.addFlashAttribute("message", message);
		return modelAndView;
	}
	
	@GetMapping("/admin/registration")
	public ModelAndView adminRegistration() {
		ModelAndView modelAndView = new ModelAndView();
		User users = new User();
		modelAndView.addObject("user", users);
		modelAndView.setViewName("adminRegistration");
		return modelAndView;
	}
	
	@PostMapping("/admin/registration")
	public ModelAndView createNewAdminUser(String username, String password, String roleName, String firstName,
			String lastName, String phone, String email, String licenseNumber, RedirectAttributes redirectAttributes) {
		String message = "";
		String pass = bCryptPasswordEncoder.encode(password);
		User users = new User();
		users.setUsername(username);
		users.setPassword(pass);

		ModelAndView modelAndView = new ModelAndView();
		User usersExists = userService.findByUserName(users.getUsername());
		if (usersExists != null) {
			message = "This User already exits";
			modelAndView.setViewName("adminRegistration");
		} else {
			roleName = "ADMIN";
			userService.saveUser(users.getUsername(), users.getPassword(), roleName, phone, email);
			modelAndView.addObject("successMessage", "Users has been registered successfully");
			modelAndView.addObject("user", new User());
			message = "User has been registered successfully";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("login");
		}
		redirectAttributes.addFlashAttribute("message", message);
		return modelAndView;
	}
	
	@GetMapping("/userProfile")
	public ModelAndView profile() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		modelAndView.addObject("userDetails",user.getUser());
		List<Event> events = eventService.getUserAssociatedEvents(user.getUser().getUserid());
		modelAndView.addObject("events", events);
		modelAndView.setViewName("userProfile");
		return modelAndView;
	}
	
	@GetMapping("/profile")
	public ModelAndView userProfile() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = (MyUserDetails) userService.loadUserByUsername(auth.getName());
		List<UserProfileDTO> userProfile = registrationService.findUserProfileByUserId(user.getUser().getUserid());
		modelAndView.addObject("userDetails",user.getUser());
		modelAndView.addObject("userProfile", userProfile);
		modelAndView.setViewName("profile");
		return modelAndView;
	}

}
