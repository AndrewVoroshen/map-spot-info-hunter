package com.voroshen.mapspotinfohunterapi;

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