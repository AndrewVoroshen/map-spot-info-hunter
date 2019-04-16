package com.voroshen.mapspotinfohunterapi.user.service;

import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;

public interface UserService {

	UserEntity create(UserEntity user);

	UserEntity get(Long id);
}
