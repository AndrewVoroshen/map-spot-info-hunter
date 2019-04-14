package com.voroshen.mapspotinfohunterapi.security.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

	@NotBlank
	@Size(min = 3, max = 60)
	private String username;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
}