package com.peer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.peer.exception.PeerException;
import com.peer.model.Peer;
import com.peer.model.Pet;
import com.peer.service.PeerService;

@RestController
@RequestMapping(value = "consumer")
public class PeerController {

	private static final Logger LOGGER = LogManager.getLogger(PeerController.class);

	@Autowired
	PeerService peerService;

	// USER REGISTRATION
	@PostMapping(value = "/user/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addPeer(@Valid @RequestBody Peer peer) throws PeerException {
		LOGGER.info("Client Requseted to add a user");
		ResponseEntity<String> responseEntity = null;
		String status = peerService.addPeer(peer);
		if (status != null) {
			responseEntity = new ResponseEntity<String>(status, HttpStatus.CREATED);
		}return responseEntity;
	}

	// USER LOGIN
	@GetMapping(value = "/user/login/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> loginPeer(@PathVariable String userName, @PathVariable String password) throws PeerException {
		LOGGER.info("Client Requseted to login ");
		ResponseEntity<List<Pet>> pets = null;
		pets = peerService.authenticatePeer(userName, password);
		return pets;
	}

	// DISPLAY USERS
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> listPeers() throws PeerException {
		LOGGER.info("Client Requseted to see list of available pets ");
		List<Peer> peers = null;
		List<Object> errorMessageObject = null;
		peers = peerService.listPeers();

		if (peers != null) {
			List<Object> peerObject = new ArrayList<Object>();
			peerObject.addAll(peers);
			LOGGER.info("Client got list of pets");
			return peerObject;
		} else {
			errorMessageObject = new ArrayList<Object>();
			errorMessageObject.add("No User Registered");
			LOGGER.info("Client not got any pet, as pets are not available");
		}
		return errorMessageObject;
	}

	// VIEW PETS BY USER ID
	@GetMapping(value = "/pets/myPets/{userId}")
	public List<Pet> myPets(@PathVariable long userId) throws PeerException {
		LOGGER.info("Client Requseted to see his own pet");
		List<Pet> pets = null;
		pets = peerService.getMyPets(userId);
		if (pets != null && pets.size() > 0) {
			return pets;
		}
		return pets;
	}

	// BUY A PET
	@PutMapping(value = "/pets/buyPet/{userId}/{petId}")
	public ResponseEntity<String> buyPet(@PathVariable long userId, @PathVariable long petId) throws PeerException {
		LOGGER.info("Client Requseted to buy a pet");
		ResponseEntity<String> responseEntity = null;
		responseEntity = peerService.buyPet(userId, petId);
		if (responseEntity.getBody().equals("Pet Bought")) {
			LOGGER.info("Client bought a pet with id " + petId);
			return responseEntity = new ResponseEntity<String>("Congratulations! You bought a pet", HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Check the input id(s) - (userId and petId)",
					HttpStatus.OK);
			LOGGER.info("Client Requseted with a wrong pet details");
		}
		return responseEntity;
	}

	// DELETE USER
	@DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePeer(@PathVariable long userId) throws PeerException {
		LOGGER.info("Client Requseted to delete a pet");
		String status = peerService.removePeer(userId);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(status, HttpStatus.GONE);
		return responseEntity;
	}
}
