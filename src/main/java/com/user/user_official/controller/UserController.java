package com.user.user_official.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.user_official.models.User;
import com.user.user_official.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User created  = userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody Map<String, String> body){
		String username = body.get("username");
		String password = body.get("password");
		
		boolean success = userService.login(username, password);
		if(success) {
			return ResponseEntity.ok("Login bem-sucedido");
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas");
		}
	}
}
