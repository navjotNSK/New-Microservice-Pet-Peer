package com.peer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peer.dao.PeerDao;
import com.peer.exception.PeerException;
import com.peer.model.Peer;
import com.peer.model.Pet;

@Service
public class PeerServiceImpl implements PeerService {

	@Autowired
	private PeerDao peerDao;

	@Autowired
	private PetFeignClient petFeignClient;

	@Override
	public String addPeer(Peer peer) throws PeerException {
		String status = null;
		if (peer != null && peer.getUserName() != null && peer.getPassword() != null) {
			status = peerDao.addPeer(peer);
			return status;
		} else {
			throw new PeerException("Invalid Input Details");
		}
	}

	@Override
	public Peer updatePeer(Peer peer) throws PeerException {
		Peer dummyPeer = null;
		if (peer != null && peer.getId() > 0 && peer.getUserName() != null && peer.getPassword() != null) {
			dummyPeer = peerDao.updatePeer(peer);
		} else {
			throw new PeerException("Invalid Input Details");
		}
		return dummyPeer;
	}

	@Override
	public List<Peer> listPeers() throws PeerException {
		List<Peer> peerList = peerDao.listPeers();
		if (peerList != null) {
			return peerList;
		} else {
			throw new PeerException("Invalid Input Details");
		}
	}

	/*
	 * @Override public Peer getPeerById(long userId) { Peer dummyPeer = null; if
	 * (userId > 0) { dummyPeer = peerDao.getUserById(userId); } return dummyPeer; }
	 */

	/*
	 * @Override public Peer findByUserName(String username) { Peer peer = null; if
	 * (username != null) { peer = peerDao.findByPeerName(username); } return peer;
	 * }
	 */

	@Override
	public String removePeer(long userId) throws PeerException {
		String status = null;
		if (userId > 0) {
			status = peerDao.removePeer(userId);
		}
		if (status.equals("User Deleted Successfully")) {
			return status;
		} else {
			throw new PeerException("Invalid Input Details");
		}
	}

	@Override
	public ResponseEntity<List<Pet>> authenticatePeer(String userName, String password) throws PeerException {
		String status = null;
		ResponseEntity<List<Pet>> pets = null;
		if (userName != null && password != null) {
			status = peerDao.authenticatePeer(userName, password);
			if (status.equals("Success")) {
				pets = petFeignClient.getPets();
			}
		} else {
			throw new PeerException("Invalid Input Details");
		}
		return pets;
	}

	@Override
	public ResponseEntity<String> buyPet(long userId, long petId) throws PeerException {
		ResponseEntity<String> petBought = null;

		if (userId > 0 && petId > 0) {
			petBought = peerDao.buyPet(userId, petId);
		} else {
			throw new PeerException("Invalid Input Details");
		}
		return petBought;
	}

	@Override
	public List<Pet> getMyPets(long userId) throws PeerException {
		ResponseEntity<List<Pet>> pets = null;
		List<Pet> petsList = null;
		if (userId > 0) {
			pets = petFeignClient.getPetsByUserId(userId);
			petsList = pets.getBody();
		} else {
			throw new PeerException("Invalid Input Details");
		}
		return petsList;
	}

}
