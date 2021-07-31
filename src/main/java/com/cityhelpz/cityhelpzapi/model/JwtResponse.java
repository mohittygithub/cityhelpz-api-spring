package com.cityhelpz.cityhelpzapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class JwtResponse {
	
	private String jwtToken;
	
	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@JsonGetter(value = "JWT")
	public String getJwtToken() {
		return jwtToken;
	}
	
	

}
