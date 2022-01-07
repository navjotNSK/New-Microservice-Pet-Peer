package com.peer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peer.exception.PeerException;
import com.peer.model.Peer;
import com.peer.model.Pet;

public interface PeerService {

	public abstract String addPeer(Peer peer) throws PeerException;

	public abstract Peer updatePeer(Peer peer) throws PeerException;

	public abstract List<Peer> listPeers() throws PeerException;

	//public abstract Peer getPeerById(long userId);
	
	//public abstract Peer findByUserName(String username);

	public abstract String removePeer(long userId) throws PeerException;

	public abstract ResponseEntity<List<Pet>> authenticatePeer(String userName, String password) throws PeerException;
	
	public abstract ResponseEntity<String> buyPet(long userId, long petId) throws PeerException;

	public abstract List<Pet> getMyPets(long userId) throws PeerException;
	
}
