package com.voroshen.mapspotinfohunterapi.spot.mapper;

import com.voroshen.mapspotinfohunterapi.place.mapper.PlaceMapper;
import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;
import com.voroshen.mapspotinfohunterapi.spot.vo.SpotRequest;
import com.voroshen.mapspotinfohunterapi.spot.vo.SpotResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlaceMapper.class})
public interface SpotMapper {

	@Mappings({
			@Mapping(source = "lat", target = "lat"),
			@Mapping(source = "lng", target = "lng")
	})
	@BeanMapping(ignoreByDefault = true)
	SpotEntity spotRequestToSpotEntity(SpotRequest request);

	@Mappings({
			@Mapping(source = "placeEntities", target = "placeResponses")
	})
	SpotResponse spotEntityToSpotResponse(SpotEntity entity);

	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<SpotResponse> spotEntityListToSpotResponseList(List<SpotEntity> spotEntities);

	void merge(SpotEntity source, @MappingTarget SpotEntity target);
}
