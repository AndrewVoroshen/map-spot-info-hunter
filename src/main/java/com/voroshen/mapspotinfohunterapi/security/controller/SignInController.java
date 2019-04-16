package com.voroshen.mapspotinfohunterapi.security.controller;

import com.voroshen.mapspotinfohunterapi.security.provider.TokenProvider;
import com.voroshen.mapspotinfohunterapi.security.vo.SignInRequest;
import com.voroshen.mapspotinfohunterapi.security.vo.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

// TODO: refactor to services mb?
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignInController {

	private final AuthenticationManager authenticationManager;

	private final TokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(
				SignInResponse.builder()
						.token(jwt)
						.type("Bearer")
						.username(userDetails.getUsername())
						.authorities(userDetails.getAuthorities())
						.build()
		);
	}

}
