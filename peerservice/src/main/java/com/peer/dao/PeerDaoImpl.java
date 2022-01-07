package com.peer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.peer.exception.PeerException;
import com.peer.model.Peer;
import com.peer.model.Pet;
import com.peer.repository.PeerRepository;
import com.peer.service.PetFeignClient;

@Repository
public class PeerDaoImpl implements PeerDao {

	@Autowired
	private PeerRepository peerRepository;

	@Autowired
	private PetFeignClient petFeignClient;

	@Override
	public String addPeer(Peer peer) throws PeerException{
		String status = null;

		Peer dummyPeerOne = peerRepository.findByName(peer.getUserName());
		if (dummyPeerOne == null) {
			Peer dummyPeerTwo = peerRepository.save(peer);
			if (dummyPeerTwo != null) {
				status = "User Registerd Successfully";
			}
			else {
				throw new PeerException("Invalid Input Details");
			}
		}
		return status;
	}

	@Override
	public Peer updatePeer(Peer peer) throws PeerException{
		Peer dummyPeer = null;
		Optional<Peer> optionalUser = peerRepository.findById(peer.getId());
		if (optionalUser.isPresent()) {
			dummyPeer = peerRepository.save(peer);
		}else {
			throw new PeerException("Invalid Input Details");
		}
		return dummyPeer;
	}

	@Override
	public List<Peer> listPeers() throws PeerException{
		List<Peer> peerList = peerRepository.findAll();
		if(peerList!=null) {
			return peerList;
		}else {
			throw new PeerException("Invalid Input Details");
		}
	}

	@Override
	public String removePeer(long userId) throws PeerException{
		Optional<Peer> optionalUser = peerRepository.findById(userId);
		if (optionalUser.isPresent()) {
			peerRepository.deleteById(userId);
			return "User Deleted Successfully";
		} else {
			throw new PeerException("Invalid Input Details");
		}
	}

	@Override
	public String authenticatePeer(String userName, String password) throws PeerException{
		Peer peer = peerRepository.findByNameAndPassword(userName, password);
		if (peer != null && peer.getUserName().equals(userName) && peer.getPassword().equals(password)) {
			return "Success";
		}else {
			throw new PeerException("Invalid Credentials");
		}
	}

	/*
	 * @Override public Peer findByPeerName(String username) { return
	 * peerRepository.findByName(username); }
	 */

	@Override
	public ResponseEntity<String> buyPet(long userId, long petId) throws PeerException{
		ResponseEntity<String> status = null;
		Optional<Peer> optionalPeer = peerRepository.findById(userId);
		Optional<Pet> optionalPet = petFeignClient.getPetById(petId);
		if (optionalPeer.isPresent() && optionalPet.get().getOwner()==0) {
			System.out.println("Updation Request from peer dao");
			return petFeignClient.updatePetOwner(userId,petId);
		}else {
			throw new PeerException("Invalid Input Details , you can't buy this pet");
		}
	}

	@Override
	public String deletePeer(long userId) throws PeerException{
		Optional<Peer> optionalUser = peerRepository.findById(userId);
		if (optionalUser.isPresent()) {
		    peerRepository.deleteById(userId);
		    return "User Deleted Successfully";
		} else {
			throw new PeerException("Invalid user id : "+userId);
		}
	}

	/*
	 * @Override public List<Pet> getMyPets(long userId) { return
	 * petRepository.findByUserId(userId); }
	 */

	/*
	 * @Override public Peer getUserById(long userId) { Peer peer = null;
	 * Optional<Peer> optionalUser = peerRepository.findById(userId);
	 * 
	 * if (optionalUser.isPresent()) { peer = optionalUser.get(); } return peer; }
	 */
}
