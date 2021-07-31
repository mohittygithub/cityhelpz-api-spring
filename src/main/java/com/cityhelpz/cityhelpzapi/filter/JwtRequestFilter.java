package com.cityhelpz.cityhelpzapi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cityhelpz.cityhelpzapi.model.MyUserDetails;
import com.cityhelpz.cityhelpzapi.service.MyUserDetailsService;
import com.cityhelpz.cityhelpzapi.utils.JwtUtils;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtils jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

	String userName = null;
	String jwt = null;

	
	if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(jwt);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			MyUserDetails userDetails = this.myUserDetailsService.loadUserByUsername(userName);

			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
			
		chain.doFilter(request, response);
	}
}



