package com.voroshen.mapspotinfohunterapi.security.service.impl;

import com.voroshen.mapspotinfohunterapi.security.principle.UserPrinciple;
import com.voroshen.mapspotinfohunterapi.security.service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public Long getCurrentUserId() {
		SecurityContext secureContext = SecurityContextHolder.getContext();
		Authentication authentication = secureContext.getAuthentication();
		UserPrinciple principle = (UserPrinciple) authentication.getPrincipal();
		return principle.getId();
	}
}
