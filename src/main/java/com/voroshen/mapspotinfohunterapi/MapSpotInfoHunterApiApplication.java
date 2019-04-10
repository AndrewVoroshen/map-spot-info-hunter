package com.voroshen.mapspotinfohunterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapSpotInfoHunterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapSpotInfoHunterApiApplication.class, args);
	}
}


//		GeoApiContext context = new GeoApiContext.Builder()
//				.apiKey("AIzaSyCjhz1msYO5dhUHUiUfnE7MaYLy3cynL6w")
//				.build();
//		GeocodingResult[] results = new GeocodingResult[0];
//		try {
//			results = GeocodingApi.geocode(context,
//					"1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//		} catch (ApiException | InterruptedException | IOException e) {
//			e.printStackTrace();
//		}
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
