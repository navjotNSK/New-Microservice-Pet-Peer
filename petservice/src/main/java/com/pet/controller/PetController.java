package com.pet.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.exception.PetException;
import com.pet.model.Pet;
import com.pet.service.PetService;

@RestController
@RequestMapping(value = "producer")
public class PetController {

	private static final Logger LOGGER = LogManager.getLogger(PetController.class);

	@Autowired
	private PetService petService;

	// ADD A PET
	@PostMapping(value = "/pets/addPet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addPet(@RequestBody Pet pet) throws PetException {
		LOGGER.info("Client Requseted to addPet");
		ResponseEntity<String> responseEntity = null;
		String status;
		status = petService.savePet(pet);
		if (status != null) {
			responseEntity = new ResponseEntity<String>(status, HttpStatus.CREATED);
		}
		return responseEntity;
	}

	// DISPLAY ALL PETS
	@GetMapping(value = "/pets", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> petHome() throws PetException {
		LOGGER.info("Client Requseted to see all the Pet");
		List<Pet> pets = null;
		ResponseEntity<List<Pet>> responseEntity = null;
		pets = petService.getAllPets();
		if (pets != null) {
			responseEntity = new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
		}
		return responseEntity;
	}

	// VIEW BASED ON PET ID
	@GetMapping(value = "/pets/petDetail/{petId}")
	public Optional<Pet> petDetail(@PathVariable long petId) throws PetException {
		Optional<Pet> optionalPet = null;
		optionalPet = petService.getPetById(petId);
		return optionalPet;
	}

	// UPDATE PET
	@PutMapping(value = "/pets/updatePet")
	public ResponseEntity<Object> updatePetDetails(@RequestBody Pet pet) throws PetException {
		LOGGER.info("Client Requseted to update Pet ");
		Pet dummyPet = null;
		ResponseEntity<Object> responseEntity = null;
		dummyPet = petService.updatePet(pet);
		if (dummyPet != null) {
			responseEntity = new ResponseEntity<Object>(pet, HttpStatus.ACCEPTED);
			LOGGER.info("Pet is updated : " + dummyPet.toString());
		}
		return responseEntity;
	}

	// Buy A Pet
	@PutMapping(value = "/pets/updatePet/{userId}/{petId}")
	public ResponseEntity<String> updatePetById(@PathVariable long userId, @PathVariable long petId)
			throws PetException {
		LOGGER.info("Client Requseted to buy Pet ");
		int row = 0;
		ResponseEntity<String> responseEntity = null;
		row = petService.updatePetById(userId, petId);
		if (row > 0) {
			responseEntity = new ResponseEntity<String>("Pet Bought", HttpStatus.OK);
		}
		return responseEntity;
	}

	// DELETE PET BY ID
	@DeleteMapping(value = "/pets/deletePet/{petId}")
	public ResponseEntity<String> deletePetById(@PathVariable long petId) throws PetException {
		LOGGER.info("Client requested for delete a pet with id " + petId);
		ResponseEntity<String> responseEntity = null;
		String status;
			status = petService.deletePetById(petId);
			if (status != null) {
				responseEntity = new ResponseEntity<String>(status, HttpStatus.GONE);
				LOGGER.info("Pet with id " + petId + " is deleted");
			}
		return responseEntity;
	}

	// Find by user ID
	@GetMapping(value = "/myPets/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> findByUserId(@PathVariable long userId) throws PetException {
		LOGGER.info("Client Requseted to see owned Pets");
		List<Pet> pets = null;
		ResponseEntity<List<Pet>> responseEntity = null;
			pets = petService.findByUserId(userId);
			if (pets != null) {
				responseEntity = new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
			}
		return responseEntity;
	}
}
