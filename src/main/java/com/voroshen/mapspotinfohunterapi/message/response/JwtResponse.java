package com.voroshen.mapspotinfohunterapi.message.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
public class JwtResponse {

	private String token;

	private String type;

	private String username;

	private Collection<? extends GrantedAuthority> authorities;
}