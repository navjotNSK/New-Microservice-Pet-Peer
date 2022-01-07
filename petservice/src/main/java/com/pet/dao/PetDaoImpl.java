package com.pet.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.controller.PetController;
import com.pet.exception.PetException;
import com.pet.model.Pet;
import com.pet.repository.PetRepository;

@Repository
public class PetDaoImpl implements PetDao {

	private static final Logger LOGGER = LogManager.getLogger(PetController.class);

	@Autowired
	private PetRepository petRepository;

	@Override
	public String savePet(Pet pet) throws PetException {
		String status = null;
		Pet dummyPet = petRepository.save(pet);
		if (dummyPet != null) {
			status = "Pet Registerd Successsfully";
			LOGGER.info("Pet Registered");
		} else {
			throw new PetException("Enter Valid Pet Details");
		}
		return status;
	}

	@Override
	public List<Pet> getAllPets() throws PetException {
		List<Pet> petList = petRepository.findAll();
		if (petList != null) {
			return petList;
		} else {
			throw new PetException("No Pets are there in DataBase");
		}
	}

	@Override
	public Optional<Pet> getPetById(long petId) throws PetException {
		Optional<Pet> optionalPet = petRepository.findById(petId);
		if (optionalPet.isPresent()) {
			return optionalPet;
		} else {
			throw new PetException("Invalid Pet id :" + petId + " OR No found in DataBase");
		}
	}

	@Override
	public Pet updatePet(Pet pet) throws PetException {
		Optional<Pet> optionalPet = petRepository.findById(pet.getId());
		if (optionalPet.isPresent()) {
			return petRepository.save(pet);
		} else {
			throw new PetException("Invalid Pet id :" + pet.getId() + " OR No found in DataBase");
		}
	}

	@Override
	public String deletePetById(long petId) throws PetException {
		Optional<Pet> optionalPet = petRepository.findById(petId);
		if (optionalPet.isPresent()) {
			petRepository.deleteById(petId);
			LOGGER.info("Pet deleted");
			return "Pet Deleted";
		} else {
			throw new PetException("Invalid Pet id :" + petId + " OR No found in DataBase");
		}
	}

	@Override
	public int updatePetById(long userId, long petId) throws PetException {
		int row = 0;
		if ((userId > 0 && petId > 0)) {
			row = petRepository.updatePetByUserId(userId, petId);
			return row;
		} else {
			throw new PetException("Invalid Input Details");
		}
	}

	@Override
	public List<Pet> findByUserId(long userId) throws PetException {
		if (userId > 0) {
			return petRepository.findByUserId(userId);
		} else {
			throw new PetException("Invalid Input Details");
		}
	}
}
