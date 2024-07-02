package com.adbms.eventManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adbms.eventManagement.entity.Roles;
import com.adbms.eventManagement.entity.User;
import com.adbms.eventManagement.repo.RolesRepo;
import com.adbms.eventManagement.repo.UserRepo;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RolesRepo roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new MyUserDetails(user);
	}

	public User findByUserName(String username) {
		User user = userRepository.findByUserName(username);
		return user;
	}

	public Roles getRole(String role) {
		return roleRepository.findRoleByName(role);
	}

	public void saveUser(String username, String password, String role, String phone,
			String email ) {
		Roles roles = new Roles();
		roles = getRole(role);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRoles(roles);
		user.setEmail(email);
		user.setPhone(phone);
		userRepository.save(user);
	}

}
