package com.vv.personaddresszipcode.controller;

import com.vv.personaddresszipcode.model.Person;
import com.vv.personaddresszipcode.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/persons" )
public class PersonController {
    private final PersonRepository personRepository;

    @Autowired
    public PersonController( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    @GetMapping( "/zipcode/{zipCode}" )
    public List<Person> getPersonsByZipCode( @PathVariable String zipCode ) {
        return personRepository.findByAddressZipCode( zipCode );
    }
}

