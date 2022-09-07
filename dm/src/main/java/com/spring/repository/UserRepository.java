package com.spring.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User getUserByUserNo(Long userNo);
	
	public User getUserById(String id);
	
	public List<User> findAllByName(String name);
	
	public List<User> findAllByNameAndEmail(String name, String email);
	
	public List<User> findAllByIdAndEmail(String id, String email);

	public User findById(String id);

	
	@Transactional
	@Modifying
	@Query(value = "update user set password =:password where id = :id", nativeQuery = true)
	public void updatePassword(@Param("password")String password, @Param("id")String id);

	public User findByEmail(String email);

}
