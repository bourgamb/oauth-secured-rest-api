package com.bourg.secured.oauthsecuredrestapi.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bourg.secured.oauthsecuredrestapi.models.User;

@RestController
public class UserController {

	@GetMapping(value="/user", produces={"application/json"})
	public ResponseEntity<User> getUser() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Object credentials = authentication.getCredentials();
		Object details = authentication.getDetails();
		String name = authentication.getName();
		Object principal = authentication.getPrincipal();
				
		return ResponseEntity.ok().body(new User("Ambrose", "Bourg", 10));
		
	}
	
}
