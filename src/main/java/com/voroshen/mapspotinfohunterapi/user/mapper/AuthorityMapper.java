package com.voroshen.mapspotinfohunterapi.user.mapper;

import com.voroshen.mapspotinfohunterapi.user.entity.AuthorityEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

	default String entityToVo(AuthorityEntity entity) {
		return entity.getType().name();
	}
}
