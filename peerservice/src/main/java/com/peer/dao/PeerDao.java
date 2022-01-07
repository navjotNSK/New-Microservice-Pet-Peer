package com.peer.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.peer.exception.PeerException;
import com.peer.model.Peer;


public interface PeerDao {

	public abstract String addPeer(Peer peer) throws PeerException;

	public abstract Peer updatePeer(Peer peer) throws PeerException;

	public abstract List<Peer> listPeers() throws PeerException;
	
	//public abstract Peer getUserById(long userId);

	public abstract String removePeer(long userId) throws PeerException;

	public abstract String authenticatePeer(String userName, String password) throws PeerException;

	//public abstract Peer findByPeerName(String username);

	public abstract ResponseEntity<String> buyPet(long userId, long petId) throws PeerException;

	public abstract String deletePeer(long userId) throws PeerException;

	//public abstract List<Pet> getMyPets(long userId);
	
}
