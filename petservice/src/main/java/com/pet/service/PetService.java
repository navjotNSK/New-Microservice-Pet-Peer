package com.pet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pet.exception.PetException;
import com.pet.model.Pet;

@Service
public interface PetService {

	public abstract String savePet(Pet pet) throws PetException;

	public abstract List<Pet> getAllPets() throws PetException;

	public abstract Optional<Pet> getPetById(long petId) throws PetException;

	public abstract Pet updatePet(Pet pet) throws PetException;

	public abstract String deletePetById(long petId) throws PetException;
	
	public abstract int updatePetById(long userId, long petId) throws PetException;

	public abstract List<Pet> findByUserId(long userId) throws PetException;
}
