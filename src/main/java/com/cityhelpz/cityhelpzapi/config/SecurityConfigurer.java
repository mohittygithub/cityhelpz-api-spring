package com.cityhelpz.cityhelpzapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cityhelpz.cityhelpzapi.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		 String[] staticResources  =  {
			        "/images/**",
			    };

		httpSecurity
			.cors().and()
			.csrf().disable().authorizeRequests()
			.antMatchers(staticResources).permitAll()
			.antMatchers("/admin/*").permitAll()//hasAuthority("ROLES_ADMIN")
			.antMatchers("/api/v1/authenticate").permitAll()
			.antMatchers("/api/v1/users/create").permitAll()
			.antMatchers("/authenticate/mobileapp").permitAll()
			.antMatchers("/change/password").permitAll()
			.antMatchers("/forgot/password").permitAll()
			.anyRequest().authenticated()
//				.and()
//				.formLogin()
//				.loginPage("/admin/secure")
//				.permitAll()
			.and().exceptionHandling()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}