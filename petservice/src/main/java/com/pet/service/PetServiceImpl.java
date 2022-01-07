package com.pet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.dao.PetDao;
import com.pet.exception.PetException;
import com.pet.model.Pet;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetDao petDao;

	@Override
	public String savePet(Pet pet) throws PetException {
		String status = null;
		if (pet != null && pet.getName() != null && pet.getPlace() != null && pet.getAge() > 0) {
			status = petDao.savePet(pet);
			return status;
		} else {
			throw new PetException("Invalid Input Details");
		}
	}

	@Override
	public List<Pet> getAllPets() throws PetException {
		List<Pet> petList = petDao.getAllPets();
		if (petList != null) {
			return petList;
		} else {
			throw new PetException("No Pet are there in DB");
		}
	}

	@Override
	public Optional<Pet> getPetById(long petId) throws PetException {
		Optional<Pet> optionalPet = petDao.getPetById(petId);
		if(optionalPet.isPresent()) {
		return optionalPet;
		}else {
			throw new PetException("No Pet Found with id : "+petId);
		}
	}

	@Override
	public Pet updatePet(Pet pet) throws PetException {
		Pet dummyPet = null;
		if (pet.getId() > 0) {
			dummyPet = petDao.updatePet(pet);
			return dummyPet;
		}else {
			throw new PetException("Enter Valid details to update pet details");
		}
		
	}

	@Override
	public String deletePetById(long petId) throws PetException {
		String status = null;
		if (petId > 0) {
			status = petDao.deletePetById(petId);
			return status;
		}else {
			throw new PetException("Enter Valid pet id to delete pet");
		}
		
	}

	@Override
	public int updatePetById(long userId, long petId) throws PetException {
		int row = 0;
		row = petDao.updatePetById(userId, petId);
		if(row>0) {
		return row;
		}else {
			throw new PetException("Enter Valid details to buy pet");
		}
	}

	@Override
	public List<Pet> findByUserId(long userId) throws PetException {
		List<Pet> petList = petDao.findByUserId(userId);
		if(petList!=null) {
		return petList;
		}else {
			throw new PetException("Enter Valid details to get pet details");
		}
	}
}
