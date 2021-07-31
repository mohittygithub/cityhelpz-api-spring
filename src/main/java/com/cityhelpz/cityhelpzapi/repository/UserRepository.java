package com.cityhelpz.cityhelpzapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityhelpz.cityhelpzapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findById(Long id);
	User findByUsername(String username);
	

}
