package com.user.user_official.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.user_official.models.Notas;
import com.user.user_official.repository.NotasRepository;
import com.user.user_official.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NotasService {

	@Autowired
	private NotasRepository notasRepository;
	
	public Notas register(Notas notas) {
		return notasRepository.save(notas);
	}
	
	public List<Notas> listAll(){
		return notasRepository.findAll();
	}
	
	public Optional<Notas> findbyId(Long id) {
		return notasRepository.findById(id);
	}
	
	public void deleteNotas(Long id) {
		notasRepository.deleteById(id);
	}
	
	public Optional<Notas> updatedNota(Long id, Notas notasDetail){
		try {
			Optional<Notas> notasOptional = notasRepository.findById(id);
			if(!notasOptional.isPresent()) {
				return Optional.empty();
			}
			
			Notas notas = notasOptional.get();
			notas.setTitle(notasDetail.getTitle());
			notas.setDescricao(notasDetail.getDescricao());
			
			Notas updatedNotas = notasRepository.save(notas);
			return Optional.of(updatedNotas);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
