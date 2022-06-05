package com.examples.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

}

class Coffee {
	private final String id;
	private String name;

	public Coffee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Coffee(String name) {
		this(UUID.randomUUID().toString(), name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@RestController
@RequestMapping("/")
class RestApiDemoController {
	private List<Coffee> coffees = new ArrayList<>();

	public  RestApiDemoController() {
		coffees.addAll(List.of(
				new Coffee("Cafe Cereza"),
				new Coffee("Cafe Ganador"),
				new Coffee("Cafe Lareno")
		));
	}

	@GetMapping("/coffees")
	Iterable<Coffee> getCoffees() {
		return coffees;
	}

	@GetMapping("/coffees/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		for (Coffee c: coffees) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}

		return Optional.empty();
	}

}
