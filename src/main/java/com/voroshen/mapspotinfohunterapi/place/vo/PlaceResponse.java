package com.voroshen.mapspotinfohunterapi.place.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponse {

	private String id;

	private String name;

	private Float rating;
}
