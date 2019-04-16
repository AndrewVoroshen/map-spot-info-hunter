package com.voroshen.mapspotinfohunterapi.place.repository;

import com.voroshen.mapspotinfohunterapi.place.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, String> {
}
