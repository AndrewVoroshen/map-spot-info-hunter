package com.voroshen.mapspotinfohunterapi.place.service;

import com.voroshen.mapspotinfohunterapi.place.entity.PlaceEntity;

import java.util.List;

public interface PlaceService {

	List<PlaceEntity> saveAll(Double lng, Double lat, Integer radius);
}
