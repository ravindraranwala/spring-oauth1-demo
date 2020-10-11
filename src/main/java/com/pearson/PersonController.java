package com.pearson;

import java.util.Collection;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pearson.model.Person;
import com.pearson.service.PersonService;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {
	private final PersonService personService;

	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	@GetMapping("/{id}")
	Person findById(@PathVariable @Min(1) int id) {
		return personService.getPersonById(id);
	}

	@GetMapping
	Collection<Person> getAllPeople() {
		return personService.getAllPeople();
	}
	
	@PostMapping
	Person createPerson(@ModelAttribute String greeting, @RequestParam String name) {
		System.out.println(name);
		return new Person(1, "test name", "PhD", "Scientific");
	}
}
