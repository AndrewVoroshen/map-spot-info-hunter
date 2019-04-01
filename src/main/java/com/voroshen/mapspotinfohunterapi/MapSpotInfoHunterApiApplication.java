package com.voroshen.mapspotinfohunterapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MapSpotInfoHunterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapSpotInfoHunterApiApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(CustomRepository repo) {
		return args -> {
			repo.save(new CustomEntity());
			repo.save(new CustomEntity());
			repo.save(new CustomEntity());
		};
	}
}

@RestController
class ApiController {

	private final CustomRepository repo;

	@Autowired
	ApiController(CustomRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/")
	String test() {

		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey("AIzaSyDJ027nmE6e-NagCOJzUnbRl3qhLjs27_s")
				.build();
		GeocodingResult[] results = new GeocodingResult[0];
		try {
			results = GeocodingApi.geocode(context,
					"1600 Amphitheatre Parkway Mountain View, CA 94043").await();
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(results[0].addressComponents));

		return "test";
	}

	@GetMapping("/entities")
	List<CustomEntity> testEntities() {



		return repo.findAll();
	}
}

@Entity
@Table(name = "custom")
class CustomEntity {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

@Repository
interface CustomRepository extends JpaRepository<CustomEntity, Long> {

	List<CustomEntity> findAll();
}