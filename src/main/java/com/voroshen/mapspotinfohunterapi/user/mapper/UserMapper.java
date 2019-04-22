package com.voroshen.mapspotinfohunterapi.user.mapper;

import com.voroshen.mapspotinfohunterapi.security.vo.SignUpRequest;
import com.voroshen.mapspotinfohunterapi.security.vo.SignUpResponse;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapperDecorator.class, AuthorityMapper.class})
public interface UserMapper {

	@Mappings({
			@Mapping(source = "authorities", target = "authorityEntities", ignore = true),
			@Mapping(source = "password", target = "password", ignore = true)
	})
	UserEntity signUpRequestToUserEntity(SignUpRequest vo);

	@Mappings({
			@Mapping(source = "authorityEntities", target = "authorities")
	})
	SignUpResponse userEntityToSignUpResponse(UserEntity entity);
}
