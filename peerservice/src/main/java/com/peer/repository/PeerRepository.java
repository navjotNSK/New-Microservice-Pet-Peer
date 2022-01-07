package com.peer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.peer.model.Peer;

@Repository
public interface PeerRepository extends CrudRepository<Peer, Long> {

	@Query("select u from Peer u where u.userName = :userName")
	public abstract Peer findByName(@Param("userName") String userName);

	@Query("select u from Peer u where u.userName = :userName and u.password = :password")
	public abstract Peer findByNameAndPassword(@Param("userName") String userName,
			@Param("password") String password);

	public abstract List<Peer> findAll();
}
