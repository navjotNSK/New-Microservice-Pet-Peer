package com.pet.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pet.model.Pet;


@Repository
public interface PetRepository extends CrudRepository<Pet, Long>{

	public abstract List<Pet> findAll();

	@Query(value = "select * from pet_table p where p.Pet_Owner_Id= :userId", nativeQuery = true)
	public abstract List<Pet> findByUserId(@Param("userId") long userId);

	@Transactional
	@Modifying
	@Query(value = "update pet_table set pet_owner_id= :userId where pet_id = :petId", nativeQuery = true)
	public abstract int updatePetByUserId(@Param("userId") long userId, @Param("petId") long petId);
}
