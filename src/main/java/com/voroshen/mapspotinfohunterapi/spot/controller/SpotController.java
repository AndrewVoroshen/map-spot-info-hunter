package com.voroshen.mapspotinfohunterapi.spot.controller;

import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;
import com.voroshen.mapspotinfohunterapi.spot.mapper.SpotMapper;
import com.voroshen.mapspotinfohunterapi.spot.service.SpotService;
import com.voroshen.mapspotinfohunterapi.spot.vo.SpotRequest;
import com.voroshen.mapspotinfohunterapi.spot.vo.SpotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpotController {

	private final SpotService spotService;

	private final SpotMapper spotMapper;

	@PostMapping("/spot")
	@PreAuthorize("hasAuthority(T(com.voroshen.mapspotinfohunterapi.user.entity.AuthorityType).USER.toString())")
	public ResponseEntity save(@Valid @RequestBody SpotRequest spotRequest) {

		SpotEntity toCreate = spotMapper.spotRequestToSpotEntity(spotRequest);

		SpotEntity toResponse = spotService.save(toCreate);

		SpotResponse response = spotMapper.spotEntityToSpotResponse(toResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/spot/{id}")
	@PreAuthorize("hasAuthority(T(com.voroshen.mapspotinfohunterapi.user.entity.AuthorityType).USER.toString())")
	public ResponseEntity get(@PathVariable("id") Long id) {

		SpotEntity toResponse = spotService.get(id);

		SpotResponse response = spotMapper.spotEntityToSpotResponse(toResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/spot/all")
	@PreAuthorize("hasAuthority(T(com.voroshen.mapspotinfohunterapi.user.entity.AuthorityType).USER.toString())")
	public ResponseEntity getAllForCurrentUser() {

		List<SpotEntity> toResponse = spotService.getAllForCurrentUser();

		List<SpotResponse> response = spotMapper.spotEntityListToSpotResponseList(toResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/spot/all")
	@PreAuthorize("hasAuthority(T(com.voroshen.mapspotinfohunterapi.user.entity.AuthorityType).USER.toString())")
	public ResponseEntity deleteAllForCurrentUser() {

		spotService.deleteAllForCurrentUser();

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
