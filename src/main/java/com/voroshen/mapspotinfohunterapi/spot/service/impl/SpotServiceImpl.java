package com.voroshen.mapspotinfohunterapi.spot.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.voroshen.mapspotinfohunterapi.global.util.GeocodingMapper;
import com.voroshen.mapspotinfohunterapi.global.util.JsonRestCountriesMapper;
import com.voroshen.mapspotinfohunterapi.place.service.PlaceService;
import com.voroshen.mapspotinfohunterapi.security.service.SecurityService;
import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;
import com.voroshen.mapspotinfohunterapi.spot.mapper.SpotMapper;
import com.voroshen.mapspotinfohunterapi.spot.repository.SpotRepository;
import com.voroshen.mapspotinfohunterapi.spot.service.SpotService;
import com.voroshen.mapspotinfohunterapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpotServiceImpl implements SpotService {

	@Value("${openweathermap.apiKey}")
	private String openWeatherMapApiKey;

	private final RestTemplate restTemplate;

	private final SecurityService securityService;

	private final GeoApiContext context;

	private final PlaceService placeService;

	private final SpotRepository spotRepository;

	private final UserService userService;

	private final SpotMapper spotMapper;

	@Override
	public SpotEntity save(SpotEntity toCreate) {

		Optional<SpotEntity> savedSpotOpt = spotRepository.findByLngAndLat(toCreate.getLng(), toCreate.getLat());

		if (savedSpotOpt.isPresent()) {
			spotMapper.merge(savedSpotOpt.get(), toCreate);
		} else {
			GeocodingMapper.map(toCreate, getGeocodingData(toCreate.getLng(), toCreate.getLat()));
			JsonRestCountriesMapper.map(toCreate, getRestCountriesData(toCreate.getCountry()));
			toCreate.setRadius((10000 / (toCreate.getPopulationDensity() > 1 ? toCreate.getPopulationDensity().intValue() : 1)));
			toCreate.setPlaceEntities(new HashSet<>(placeService.saveAll(toCreate.getLng(), toCreate.getLat(), toCreate.getRadius())));
		}

		toCreate.getUsers().add(userService.get(securityService.getCurrentUserId()));

		return spotRepository.save(toCreate);
	}

	@Override
	public SpotEntity get(Long id) {
		Long currentUserId = securityService.getCurrentUserId();
		return spotRepository.findByIdAndUsersIdIn(id, currentUserId)
				.orElseThrow(() -> new RuntimeException(String.format("Spot with id: %s for user with id: %s not found!", id, currentUserId)));
	}

	@Override
	public List<SpotEntity> getAllForCurrentUser() {
		Long currentUserId = securityService.getCurrentUserId();
		return spotRepository.findAllByUsersIdIn(currentUserId);
	}

	@Override
	public void deleteAllForCurrentUser() {
		Long currentUserId = securityService.getCurrentUserId();
		List<SpotEntity> toUpdate = spotRepository.findAllByUsersIdIn(currentUserId);
		toUpdate.forEach(e -> deleteUserFromSpotEntity(currentUserId, e));
	}

	public void deleteUserFromSpotEntity(Long userId, SpotEntity entity) {
		entity.getUsers().remove(userService.get(userId));
		if (CollectionUtils.isEmpty(entity.getUsers())) {
			spotRepository.delete(entity);
		} else {
			spotRepository.save(entity);
		}
	}

	@Override
	public String getOpenWeatherData(Double lng, Double lat) {
		return restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lng}&units=metric&APPID={openWeatherMapApiKey}",
				String.class,
				lat,
				lng,
				openWeatherMapApiKey
		);
	}

	private String getRestCountriesData(String country) {
		return restTemplate.getForObject(
				"https://restcountries.eu/rest/v2/name/{name}?fields=population;area;currencies;languages",
				String.class,
				country
		);
	}

	private GeocodingResult getGeocodingData(Double lng, Double lat) {
		try {
			GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(lat, lng)).await();
			return results[0];
		} catch (ApiException | InterruptedException | IOException | NullPointerException | IndexOutOfBoundsException e) {
			throw new RuntimeException("Bad coords!");
		}
	}
}
