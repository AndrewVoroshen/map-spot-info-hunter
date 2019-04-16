package com.voroshen.mapspotinfohunterapi.global.config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoApiConfig {

	@Value("${google.maps.apiKey}")
	private String googleApiKey;

	@Bean
	public GeoApiContext geoApiContext() {
		return new GeoApiContext.Builder()
				.apiKey(googleApiKey)
				.build();
	}
}
