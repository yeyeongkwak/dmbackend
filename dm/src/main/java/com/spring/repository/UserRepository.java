package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User getUserByUserNo(Long userNo);
	
	public User getUserById(String id);
	
	public List<User> findAllByName(String name);

}
