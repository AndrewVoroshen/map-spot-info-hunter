package com.voroshen.mapspotinfohunterapi.global.util.openweathermodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Main {

	private String temp;

	private String humidity;

	private String pressure;
}
