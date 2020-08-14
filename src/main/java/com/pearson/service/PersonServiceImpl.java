package com.pearson.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pearson.model.Person;

@Service
public final class PersonServiceImpl implements PersonService {
	private final RestTemplate restTemplate;

	public PersonServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		super();
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Person getPersonById(int id) {
		return restTemplate.getForObject("http://www.mocky.io/v2/5e378a773100000f00d37ae2", Person.class);
	}

	@Override
	public Collection<Person> getAllPeople() {
		final Person[] people = restTemplate.getForObject("http://www.mocky.io/v2/5e378e123100006c00d37af2",
				Person[].class);
		return Arrays.asList(people);
	}
}
