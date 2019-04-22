package com.voroshen.mapspotinfohunterapi.global.util;

import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.voroshen.mapspotinfohunterapi.place.entity.PlaceEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlacesMapper {

	public static List<PlaceEntity> map(PlacesSearchResponse response) {

		List<PlaceEntity> places = new ArrayList<>();

		if (response.results != null) {
			Arrays.asList(response.results).forEach(place -> places.add(create(place)));
		}

		return places;
	}

	private static PlaceEntity create(PlacesSearchResult result) {
		return PlaceEntity.builder()
				.id(result.placeId)
				.rating(result.rating)
				.name(result.name)
				.build();
	}
}
