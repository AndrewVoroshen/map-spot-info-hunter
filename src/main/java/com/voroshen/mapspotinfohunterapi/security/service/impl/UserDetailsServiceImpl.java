package com.voroshen.mapspotinfohunterapi.security.service.impl;

import com.voroshen.mapspotinfohunterapi.security.principle.UserPrinciple;
import com.voroshen.mapspotinfohunterapi.security.service.SecurityService;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import com.voroshen.mapspotinfohunterapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService, SecurityService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return UserPrinciple.build(userEntity);
	}

	@Override
	public Long getCurrentUserId() {
		SecurityContext secureContext = SecurityContextHolder.getContext();
		Authentication authentication = secureContext.getAuthentication();
		UserPrinciple principle = (UserPrinciple) authentication.getPrincipal();
		return principle.getId();
	}
}