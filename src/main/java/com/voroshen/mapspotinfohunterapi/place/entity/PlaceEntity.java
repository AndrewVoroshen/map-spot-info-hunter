package com.voroshen.mapspotinfohunterapi.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "place")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceEntity {

	@Id
	private String id;

	private String name;

	private Float rating;
}
