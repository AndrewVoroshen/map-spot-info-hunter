package com.voroshen.mapspotinfohunterapi.global.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voroshen.mapspotinfohunterapi.global.util.restcoutriesmodel.Currency;
import com.voroshen.mapspotinfohunterapi.global.util.restcoutriesmodel.Language;
import com.voroshen.mapspotinfohunterapi.global.util.restcoutriesmodel.RestCountriesResponse;
import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;

import java.io.IOException;
import java.util.stream.Collectors;

public class JsonRestCountriesMapper {

	public static void map(SpotEntity entity, String toMap) {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			RestCountriesResponse[] responses = mapper.readValue(toMap, RestCountriesResponse[].class);
			if (responses.length > 0) {
				entity.setCurrencies(responses[0].getCurrencies().stream().map(Currency::getName).collect(Collectors.toList()));
				entity.setPopulationDensity(responses[0].getPopulation() / responses[0].getArea());
				entity.setLanguages(responses[0].getLanguages().stream().map(Language::getName).collect(Collectors.toList()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
