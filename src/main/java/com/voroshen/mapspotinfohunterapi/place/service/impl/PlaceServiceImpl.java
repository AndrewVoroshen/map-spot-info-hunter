package com.voroshen.mapspotinfohunterapi.place.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.voroshen.mapspotinfohunterapi.global.util.PlacesMapper;
import com.voroshen.mapspotinfohunterapi.place.entity.PlaceEntity;
import com.voroshen.mapspotinfohunterapi.place.repository.PlaceRepository;
import com.voroshen.mapspotinfohunterapi.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaceServiceImpl implements PlaceService {

	private final PlaceRepository placeRepository;

	private final GeoApiContext context;

	@Override
	public List<PlaceEntity> saveAll(Double lng, Double lat, Integer radius) {
		try {
			PlacesSearchResponse placesSearchResponse = PlacesApi.nearbySearchQuery(context, new LatLng(lat, lng)).radius(radius).await();
			return placeRepository.saveAll(PlacesMapper.map(placesSearchResponse));
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(PlaceEntity toDelete) {
		placeRepository.delete(toDelete);
	}
}
