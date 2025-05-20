package com.user.user_official.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.user_official.models.Joia;
import com.user.user_official.service.JoiaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/joias")
public class JoiaController {

	@Autowired
	private JoiaService joiaService;
	
	@GetMapping
	public ResponseEntity<List<Joia>> listAll(){
		List<Joia> joias = joiaService.listAll();
		return ResponseEntity.ok(joias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Joia> findById(@PathVariable Long id){
		Optional<Joia> joia = joiaService.findById(id);
		if(joia.isPresent()) {
			return ResponseEntity.ok(joia.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJoia(@PathVariable Long id){
		joiaService.deleteJoia(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Joia> registerJoia(@RequestBody Joia joia){
		Joia created = joiaService.register(joia);
		return ResponseEntity.ok().body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Joia> updateJoia(@PathVariable Long id, @RequestBody Joia joia){
		Optional<Joia> updatedJoiaOptional = joiaService.updatedJoia(id, joia);
		if(!updatedJoiaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedJoiaOptional.get());
	}
}
