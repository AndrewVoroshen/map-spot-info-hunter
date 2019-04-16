package com.voroshen.mapspotinfohunterapi.place.mapper;

import com.voroshen.mapspotinfohunterapi.place.entity.PlaceEntity;
import com.voroshen.mapspotinfohunterapi.place.vo.PlaceResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

	PlaceResponse placeEntityToPlaceResponse(PlaceEntity entity);

	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<PlaceResponse> placeEntitySetToPlaceResponseList(Set<PlaceEntity> entities);
}
