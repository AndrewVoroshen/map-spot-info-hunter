package com.voroshen.mapspotinfohunterapi.message.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String token;

	private String type = "Bearer";

	private String username;

	private Collection<? extends GrantedAuthority> authorities;
}