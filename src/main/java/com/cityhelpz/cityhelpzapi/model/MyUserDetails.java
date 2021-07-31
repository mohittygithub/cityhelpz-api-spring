package com.cityhelpz.cityhelpzapi.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class MyUserDetails implements UserDetails{
	
	private User user;
	private long id;
	private String username;
	private String password;
	private String roles;
	private boolean active;
	private List<GrantedAuthority> authorities;

	
	
	public MyUserDetails(User user) {
		super();
		this.user = user;
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.active = user.isActive();
		// ROLES field having comma separated roles, combining it as list
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}
	
	public String getRoles() {
		return roles;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
