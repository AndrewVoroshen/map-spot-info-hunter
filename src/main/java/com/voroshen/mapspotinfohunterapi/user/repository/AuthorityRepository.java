package com.voroshen.mapspotinfohunterapi.user.repository;

import com.voroshen.mapspotinfohunterapi.user.entity.AuthorityEntity;
import com.voroshen.mapspotinfohunterapi.user.entity.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

	Optional<AuthorityEntity> findByType(AuthorityType authorityType);
}