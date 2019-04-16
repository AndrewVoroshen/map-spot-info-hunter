package com.voroshen.mapspotinfohunterapi.spot.service;

import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;

import java.util.List;

public interface SpotService {

	SpotEntity save(SpotEntity toCreate);

	SpotEntity get(Long id);

	List<SpotEntity> getAllForCurrentUser();

	void deleteAllForCurrentUser();
}
