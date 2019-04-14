package com.voroshen.mapspotinfohunterapi.security.controller;

import com.voroshen.mapspotinfohunterapi.security.vo.SignUpRequest;
import com.voroshen.mapspotinfohunterapi.security.vo.SignUpResponse;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import com.voroshen.mapspotinfohunterapi.user.mapper.UserMapper;
import com.voroshen.mapspotinfohunterapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignUpController {

	private final UserMapper userMapper;

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		UserEntity toCreate = userMapper.voToEntity(signUpRequest);

		UserEntity toResponse = userService.create(toCreate);

		SignUpResponse response = userMapper.entityToVo(toResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
