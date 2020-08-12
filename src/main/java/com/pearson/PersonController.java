package com.pearson;

import java.util.Collection;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping()
	Collection<Person> getAllPeople() {
		return personService.getAllPeople();
	}
}
