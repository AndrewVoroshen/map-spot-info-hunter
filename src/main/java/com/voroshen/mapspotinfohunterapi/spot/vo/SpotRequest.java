package com.voroshen.mapspotinfohunterapi.spot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotRequest {

	@NotNull
	private Double lng;

	@NotNull
	private Double lat;
}
