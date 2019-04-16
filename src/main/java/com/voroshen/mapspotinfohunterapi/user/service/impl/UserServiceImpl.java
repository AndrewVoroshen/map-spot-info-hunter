package com.voroshen.mapspotinfohunterapi.user.service.impl;

import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import com.voroshen.mapspotinfohunterapi.user.repository.UserRepository;
import com.voroshen.mapspotinfohunterapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserEntity create(UserEntity user) {

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new RuntimeException(String.format("User with username: %s already exists", user.getUsername()));
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException(String.format("User with email: %s already exists", user.getEmail()));
		}

		return userRepository.save(user);
	}

	@Override
	public UserEntity get(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("User with id: %s not found", id)));
	}
}
