package com.voroshen.mapspotinfohunterapi.user.mapper;

import com.voroshen.mapspotinfohunterapi.user.entity.AuthorityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

	default String entityToVo(AuthorityEntity entity) {
		return entity.getType().name();
	}
}
