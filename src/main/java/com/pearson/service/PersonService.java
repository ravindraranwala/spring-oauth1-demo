package com.pearson.service;

import java.util.Collection;

import com.pearson.model.Person;

public interface PersonService {
	/**
	 * Fetches a person for a given ID if exists.
	 * 
	 * @param id person identifier value.
	 * @return Person instance with the given ID value.
	 */
	public Person getPersonById(int id);

	/**
	 * Fetches all the people in the system.
	 * 
	 * @return All the people in the system.
	 */
	public Collection<Person> getAllPeople();
}
