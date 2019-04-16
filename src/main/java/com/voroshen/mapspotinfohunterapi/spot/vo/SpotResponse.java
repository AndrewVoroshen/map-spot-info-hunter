package com.voroshen.mapspotinfohunterapi.spot.vo;

import com.voroshen.mapspotinfohunterapi.place.vo.PlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotResponse {

	private Long id;

	private Double lng;

	private Double lat;

	private Integer radius;

	private String country;

	private String address;

	private Double populationDensity;

	private List<String> languages;

	private String weather;

	private List<String> currencies;

	private List<PlaceResponse> placeResponses;
}
