package com.cityhelpz.cityhelpzapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cityhelpz.cityhelpzapi.exception.ResourceNotFoundException;
import com.cityhelpz.cityhelpzapi.model.Response;
import com.cityhelpz.cityhelpzapi.model.User;
import com.cityhelpz.cityhelpzapi.repository.UserRepository;
import com.cityhelpz.cityhelpzapi.utils.Utils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private Utils utils;
	
	private List<User> users = new ArrayList<>();
	
	// get all users
	public Response getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		
		return utils.successResponseStatus(users, "users","");
	}
	
	// get user by id
	public Response getUserById(Long userId) {
		try {
			Optional<User> allUsers = userRepository.findById(userId);
			if(!allUsers.isEmpty() && allUsers.get() != null) {
				User user = allUsers.get();
				users.add(user);
				return utils.successResponseStatus(users, "users","");
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("No user found with id : " + userId);
		}
		return new Response();
	}
	
	// create new user
	public Response createUser(User user) throws Exception {
		boolean valuesMissing = false;
		if(checkUserBody(user) == valuesMissing) {
			throw new Exception("Incomplete form data.");
		}
		
		try {
			User isUserExists = userRepository.findByUsername(user.getEmail());
			if(isUserExists != null) {
				throw new Exception("User already exists.");
			}
			
			user.setUsername(user.getEmail());
			String hashedPassword = user.getPassword();
			hashedPassword = bcrypt.encode(hashedPassword);
			user.setPassword(hashedPassword);
			user.setActive(true);
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			
			User newUser = userRepository.save(user);
			users.add(newUser);
			
			return utils.successResponseStatus(users, "users","User created successfully.");
			
		} catch (Exception e) {
			throw new Exception();
		}
		
		
	}
	
	
	
	// update user
	public Response updateUser(Long userId, User user) throws Exception {
		try {
			Optional<User> allUsers = userRepository.findById(userId);
			
			if(!allUsers.isEmpty() && allUsers.get() != null) {
				User userFound = allUsers.get();
				userFound.setName(user.getName());
				userFound.setUpdatedAt(new Date());
				userRepository.save(userFound);
				users.add(userFound);
				
				return utils.successResponseStatus(users, "users","Updated successfully."); 
			}
			
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("User not found with id : " + userId);
		}
		return null;
	}
	
	// update user
	public Response deleteUser(Long userId) throws Exception {
		try {
			Optional<User> users = userRepository.findById(userId);
			if(!users.isEmpty() && users.get() != null) {
				User user = users.get();
				userRepository.delete(user);
				
				return utils.successResponseStatus(null, "users", "User with Id " + userId + " deleted successfully.");
			}else {
				throw new ResourceNotFoundException("User not found with id : " + userId);
			}
			

			
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	// method to check request body while creating a new user
	public boolean checkUserBody(User user) {
		if(user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
			return false;
		}
		return true;
	}

}
