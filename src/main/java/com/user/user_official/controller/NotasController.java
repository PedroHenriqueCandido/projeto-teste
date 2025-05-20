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

import com.user.user_official.models.Notas;
import com.user.user_official.service.NotasService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nota")
public class NotasController {

	@Autowired
	private NotasService notasService;

	@GetMapping
	public ResponseEntity<List<Notas>> listAll() {
		List<Notas> notas = notasService.listAll();
		return ResponseEntity.ok(notas);
	}
	
	@PostMapping
	public ResponseEntity<Notas> registerNotas(@RequestBody Notas notas){
		Notas created = notasService.register(notas);
		return ResponseEntity.ok().body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Notas> updatedNotas(@PathVariable Long id, @RequestBody Notas notas){
		Optional<Notas> updatedNotasOptional = notasService.updatedNota(id, notas);
		if(!updatedNotasOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedNotasOptional.get());
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Notas> findById(@PathVariable Long id) {
		
		Optional<Notas> notas = notasService.findbyId(id);
		if(notas.isPresent()){
			return ResponseEntity.ok(notas.get());
		}
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotas(@PathVariable Long id){
		notasService.deleteNotas(id);
		return ResponseEntity.noContent().build();
	}
}
