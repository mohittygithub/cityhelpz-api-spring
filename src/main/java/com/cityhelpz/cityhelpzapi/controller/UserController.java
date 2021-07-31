package com.cityhelpz.cityhelpzapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityhelpz.cityhelpzapi.model.JwtRequest;
import com.cityhelpz.cityhelpzapi.model.JwtResponse;
import com.cityhelpz.cityhelpzapi.model.MyUserDetails;
import com.cityhelpz.cityhelpzapi.model.Response;
import com.cityhelpz.cityhelpzapi.model.User;
import com.cityhelpz.cityhelpzapi.service.MyUserDetailsService;
import com.cityhelpz.cityhelpzapi.service.UserService;
import com.cityhelpz.cityhelpzapi.utils.JwtUtils;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final MyUserDetails userDetails = myUserDetailsService
				.loadUserByUsername(jwtRequest.getUsername());

		final String jwt = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	// test route
	@GetMapping("")
	public String sayHello() {
		return "Hello";
	}
	
	// get all users
	@GetMapping("/users")
	public Response getAllUsers(){
		return userService.getAllUsers();
	}
		
	// get user by id
	@GetMapping("/users/{id}")
	public Response getUserById(@PathVariable("id") Long userId) {
		System.out.println("hi");
		return userService.getUserById(userId);
	}
	
	// create user
	@PostMapping("/users/create")
	public Response createUser(@RequestBody User user) throws Exception {
		return userService.createUser(user);
	}
	
	// update user
	@PutMapping("/users/{id}")
	public Response updateUser(@PathVariable("id") Long userId, @RequestBody User user) throws Exception {
		return userService.updateUser(userId, user);
	}
	
	// delete user
	@DeleteMapping("/users/{id}")
	public Response deleteUser(@PathVariable("id") Long userId) throws Exception {
		return userService.deleteUser(userId);
	}

	
}
