package com.voroshen.mapspotinfohunterapi.security.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {

	private Long id;

	private String username;

	private String password;

	List<String> authorities;
}
