package com.pet.dao;

import java.util.List;
import java.util.Optional;

import com.pet.exception.PetException;
import com.pet.model.Pet;


public interface PetDao {
	
	public abstract String savePet(Pet pet) throws PetException;

	public abstract List<Pet> getAllPets() throws PetException;

	public abstract Optional<Pet> getPetById(long petId) throws PetException;

	public abstract Pet updatePet(Pet pet) throws PetException;

	public abstract String deletePetById(long petId) throws PetException;

	public abstract int updatePetById(long userId, long petId) throws PetException;
	
	public abstract List<Pet> findByUserId(long userId) throws PetException;
}

