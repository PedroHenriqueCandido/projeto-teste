package com.user.user_official.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.user_official.models.User;
import com.user.user_official.repository.UserRepository;
import com.user.user_official.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	public boolean login(String email, String password) {
		return userRepository.findByEmail(email)
				.map(user -> passwordEncoder.matches(password, user.getPassword()))
				.orElse(false);
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> updateUser(Long id, User userDetail){
		try {
			Optional<User> userOptional = userRepository.findById(id);
			if(!userOptional.isPresent()) {
				return Optional.empty();
			}
			User user = userOptional.get();
			user.setEmail(userDetail.getEmail());
			user.setUsername(userDetail.getUsername());
			user.setImgUrl(userDetail.getImgUrl());
			
			User updatedUser = userRepository.save(user);
			return Optional.of(updatedUser);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
