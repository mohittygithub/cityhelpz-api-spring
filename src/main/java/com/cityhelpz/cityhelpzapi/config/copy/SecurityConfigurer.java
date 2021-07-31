package com.cityhelpz.cityhelpzapi.config.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cityhelpz.cityhelpzapi.service.MyUserDetailsService;

public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
}
