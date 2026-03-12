package com.vv.personaddresszipcode.controller;

import com.vv.personaddresszipcode.model.Person;
import com.vv.personaddresszipcode.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Marks this class as a REST controller.
// Spring will detect it and expose its methods as HTTP endpoints.
@RestController

// Base URL for all endpoints in this controller.
@RequestMapping("/persons")
public class PersonController {

    // Repository used to access person data from the database.
    private final PersonRepository personRepository;

    // Constructor injection of the repository.
    // Spring automatically provides the PersonRepository bean here.
    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Handles HTTP GET requests sent to /persons/zipcode/{zipCode}
    // Example: GET /persons/zipcode/75000
    @GetMapping("/zipcode/{zipCode}")
    public List<Person> getPersonsByZipCode(@PathVariable String zipCode) {

        // Calls the repository method to find all persons
        // whose address zip code matches the provided value.
        return personRepository.findByAddressZipCode(zipCode);
    }
}