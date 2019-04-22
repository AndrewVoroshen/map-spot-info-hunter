package com.voroshen.mapspotinfohunterapi.spot.entity;

import com.voroshen.mapspotinfohunterapi.place.entity.PlaceEntity;
import com.voroshen.mapspotinfohunterapi.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "spot")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double lng;

	private Double lat;

	private Integer radius;

	private String country;

	private String address;

	private Double populationDensity;

	@ElementCollection
	@CollectionTable(
			name = "spot_language",
			joinColumns = {@JoinColumn(name = "spot_id")}
	)
	@Column(name = "language")
	private List<String> languages;

	@ElementCollection
	@CollectionTable(
			name = "spot_currency",
			joinColumns = {@JoinColumn(name = "spot_id")}
	)
	@Column(name = "currency")
	private List<String> currencies;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "spot_place",
			joinColumns = @JoinColumn(name = "spot_id"),
			inverseJoinColumns = @JoinColumn(name = "place_id")
	)
	private Set<PlaceEntity> placeEntities = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "spot_user",
			joinColumns = @JoinColumn(name = "spot_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<UserEntity> users = new HashSet<>();
}
