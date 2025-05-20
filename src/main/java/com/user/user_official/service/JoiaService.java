package com.user.user_official.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.user_official.models.Joia;
import com.user.user_official.repository.JoiaRepository;
import com.user.user_official.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JoiaService {

	@Autowired
	private JoiaRepository joiaRepository;
	
	public List<Joia> listAll(){
		return joiaRepository.findAll();
	}
	
	public Optional<Joia> findById(Long id) {
		return joiaRepository.findById(id);
	}
	
	public void deleteJoia(Long id) {
		joiaRepository.deleteById(id);
	}
	
	public Joia register(Joia joia) {
		return joiaRepository.save(joia);
	}
	
	public Optional<Joia> updatedJoia(Long id, Joia joiaDetail){
		try {
			Optional<Joia> joiaOptional = joiaRepository.findById(id);
			if(!joiaOptional.isPresent()) {
				return Optional.empty();
			}
			
			Joia joia = joiaOptional.get();
			joia.setImgUrl(joiaDetail.getImgUrl());
			joia.setMaterial(joia.getMaterial());
			joia.setName(joiaDetail.getName());
			joia.setPrice(joiaDetail.getPrice());
			joia.setQuantity(joiaDetail.getQuantity());
			Joia updatedJoia = joiaRepository.save(joia);
			return Optional.of(updatedJoia);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	} 
	
}
