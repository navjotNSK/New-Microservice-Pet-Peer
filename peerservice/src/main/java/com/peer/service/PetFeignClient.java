package com.peer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.peer.model.Pet;

@FeignClient(name="pet-service/producer")
public interface PetFeignClient {
	
	@GetMapping(value="/pets")
	public abstract ResponseEntity<List<Pet>> getPets();
	
	@GetMapping(value="/pets/petDetail/{petId}")
	public abstract Optional<Pet> getPetById(@PathVariable("petId") long petId);
	
	@PutMapping(value="/pets/updatePet/{userId}/{petId}")
	public abstract ResponseEntity<String> updatePetOwner(@PathVariable("userId") long userId, @PathVariable("petId") long petId);

	@GetMapping(value="/myPets/{userId}")
	public abstract ResponseEntity<List<Pet>> getPetsByUserId(@PathVariable("userId") long userId);
	
}
