package com.voroshen.mapspotinfohunterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapSpotInfoHunterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapSpotInfoHunterApiApplication.class, args);
	}
}
//	@Bean
//	ApplicationRunner applicationRunner(CustomRepository repo) {
//		return args -> {
//			repo.save(new CustomEntity());
//			repo.save(new CustomEntity());
//			repo.save(new CustomEntity());
//		};
//	}

//@RestController
//class ApiController {
//
//	private final CustomRepository repo;
//
//	@Autowired
//	ApiController(CustomRepository repo) {
//		this.repo = repo;
//	}
//
//	@GetMapping("/")
//	String test() {
//
//		GeoApiContext context = new GeoApiContext.Builder()
//				.apiKey("AIzaSyDJ027nmE6e-NagCOJzUnbRl3qhLjs27_s")
//				.build();
//		GeocodingResult[] results = new GeocodingResult[0];
//		try {
//			results = GeocodingApi.geocode(context,
//					"1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//		} catch (ApiException | InterruptedException | IOException e) {
//			e.printStackTrace();
//		}
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		System.out.println(gson.toJson(results[0].addressComponents));
//
//		return "test";
//	}
//
//	@GetMapping("/entities")
//	List<CustomEntity> testEntities() {
//
//
//
//		return repo.findAll();
//	}
//}
//
//@Entity
//@Table(name = "custom")
//class CustomEntity {
//
//	@Id
//	@GeneratedValue
//	private Long id;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//}
//
//@Repository
//interface CustomRepository extends JpaRepository<CustomEntity, Long> {
//
//	List<CustomEntity> findAll();
//}