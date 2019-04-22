package com.voroshen.mapspotinfohunterapi.spot.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.voroshen.mapspotinfohunterapi.global.util.GeocodingMapper;
import com.voroshen.mapspotinfohunterapi.global.util.JsonOpenWeatherMapper;
import com.voroshen.mapspotinfohunterapi.global.util.JsonRestCountriesMapper;
import com.voroshen.mapspotinfohunterapi.place.service.PlaceService;
import com.voroshen.mapspotinfohunterapi.security.service.SecurityService;
import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;
import com.voroshen.mapspotinfohunterapi.spot.repository.SpotRepository;
import com.voroshen.mapspotinfohunterapi.spot.service.SpotService;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
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

	@Override
	public SpotEntity save(SpotEntity toCreate) {

		Optional<SpotEntity> savedSpotOpt = spotRepository.findByLngAndLat(toCreate.getLng(), toCreate.getLat());
		savedSpotOpt.ifPresent(e -> setFieldsFromExistingEntity(toCreate, e));

		toCreate.setRadius(300); // TODO: change dynamically

		JsonOpenWeatherMapper.map(toCreate, getOpenWeatherData(toCreate.getLng(), toCreate.getLat()));
		GeocodingMapper.map(toCreate, getGeocodingData(toCreate.getLng(), toCreate.getLat()));
		JsonRestCountriesMapper.map(toCreate, getRestCountriesData(toCreate.getCountry()));

		toCreate.setPlaceEntities(new HashSet<>(placeService.saveAll(toCreate.getLng(), toCreate.getLat(), toCreate.getRadius())));

		toCreate.getUsers().add(
				UserEntity.builder()
						.id(securityService.getCurrentUserId())
						.build()
		);

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
		spotRepository.save(entity);
	}

	private String getOpenWeatherData(Double lng, Double lat) {
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

	private void setFieldsFromExistingEntity(SpotEntity toCreate, SpotEntity saved) {
		toCreate.setId(saved.getId());
		toCreate.setUsers(saved.getUsers());
	}
}
