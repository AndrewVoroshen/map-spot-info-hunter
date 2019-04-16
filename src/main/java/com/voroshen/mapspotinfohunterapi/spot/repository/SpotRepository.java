package com.voroshen.mapspotinfohunterapi.spot.repository;

import com.voroshen.mapspotinfohunterapi.spot.entity.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, Long> {

	Optional<SpotEntity> findByLngAndLat(Double lng, Double lat);

	List<SpotEntity> findAllByUsersIdIn(Long userId);

	Optional<SpotEntity> findByIdAndUsersIdIn(Long id, Long userId);
}
