package com.voroshen.mapspotinfohunterapi.global.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voroshen.mapspotinfohunterapi.global.util.openweathermodel.OpenWeatherResponse;

import java.io.IOException;

public class JsonOpenWeatherConverter {

	public static String convert(String toMap) {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			OpenWeatherResponse response = mapper.readValue(toMap, OpenWeatherResponse.class);
			return "Temperature: " + response.getMain().getTemp() + " Cel. " +
					"Humidity: " + response.getMain().getHumidity() + "%. " +
					"Pressure: " + response.getMain().getPressure() + " hPa.";
		} catch (IOException e) {
			throw new RuntimeException("Unable retrieve weather data!");
		}
	}
}
