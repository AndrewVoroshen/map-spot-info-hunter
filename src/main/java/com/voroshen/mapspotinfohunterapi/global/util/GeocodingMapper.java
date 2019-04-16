package com.voroshen.mapspotinfohunterapi.global.util;

import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;

import java.util.Arrays;

public class GeocodingMapper {

	public static void map(SpotEntity entity, GeocodingResult result) {
		if (result != null) {
			Arrays.asList(result.addressComponents).forEach(addressComponent -> {
				if (Arrays.asList(addressComponent.types).contains(AddressComponentType.COUNTRY)) {
					entity.setCountry(addressComponent.longName);
				}
			});
			entity.setAddress(result.formattedAddress);
		}
	}
}
