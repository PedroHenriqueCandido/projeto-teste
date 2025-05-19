package com.user.user_official.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.user_official.models.User;
import com.user.user_official.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}
	
	public boolean login(String username, String password) {
		return userRepository.findByUsername(username)
				.map(user -> passwordEncoder.matches(password, user.getPassword()))
				.orElse(false);
	}
}
