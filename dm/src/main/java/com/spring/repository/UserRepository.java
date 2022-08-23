package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User getUserByUserNo(Long userNo);
	
	public User getUserById(String id);
	
	public List<User> findAllByName(String name);
	
	public List<User> findAllByNameAndEmail(String name, String email);
	
	public List<User> findAllByIdAndEmail(String id, String email);

	public Object findByid(String id);

	public Optional<User> findById(String id);

}
