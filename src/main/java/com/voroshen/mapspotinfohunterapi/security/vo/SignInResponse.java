package com.voroshen.mapspotinfohunterapi.security.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
public class SignInResponse {

	private String token;

	private String type;

	private String username;

	private Collection<? extends GrantedAuthority> authorities;
}