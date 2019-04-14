package com.voroshen.mapspotinfohunterapi.security.principle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

	private Long id;

	private String name;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public static UserPrinciple build(UserEntity userEntity) {

		List<GrantedAuthority> authorities = userEntity.getAuthorityEntities()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getType().name()))
				.collect(Collectors.toList());

		return new UserPrinciple(
				userEntity.getId(),
				userEntity.getName(),
				userEntity.getUsername(),
				userEntity.getEmail(),
				userEntity.getPassword(),
				authorities
		);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}