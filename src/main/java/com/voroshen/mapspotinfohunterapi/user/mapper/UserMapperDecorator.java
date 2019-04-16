package com.voroshen.mapspotinfohunterapi.user.mapper;

import com.voroshen.mapspotinfohunterapi.security.vo.SignUpRequest;
import com.voroshen.mapspotinfohunterapi.user.entity.AuthorityEntity;
import com.voroshen.mapspotinfohunterapi.user.entity.AuthorityType;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import com.voroshen.mapspotinfohunterapi.user.repository.AuthorityRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class UserMapperDecorator {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthorityRepository authorityRepository;

	@AfterMapping
	public void signUpRequestToUserEntity(final SignUpRequest request,
						   @MappingTarget final UserEntity entity) {

		entity.setPassword(encoder.encode(request.getPassword()));

		List<String> authorities = request.getAuthorities();
		Set<AuthorityEntity> authorityEntities = new HashSet<>();

		if (authorities != null) {
			authorities.forEach(authority -> {
				if (!authority.toLowerCase().equals("user")) {
					authorityEntities.add(
							authorityRepository.findByType(AuthorityType.valueOf(authority))
									.orElseThrow(() -> new RuntimeException(String.format("Authority: %s not found!", authorities)))
					);
				}
			});
		}

		authorityEntities.add(
				authorityRepository.findByType(AuthorityType.USER)
						.orElseThrow(() -> new RuntimeException(String.format("Authority: %s not found!", authorities)))
		);

		entity.setAuthorityEntities(authorityEntities);
	}
}
