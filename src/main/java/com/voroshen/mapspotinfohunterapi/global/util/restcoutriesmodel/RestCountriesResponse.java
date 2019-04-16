package com.voroshen.mapspotinfohunterapi.global.util.restcoutriesmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestCountriesResponse {

	private Long population;

	private Double area;

	private List<Currency> currencies;

	private List<Language> languages;
}
