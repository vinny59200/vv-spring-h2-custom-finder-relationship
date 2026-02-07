package com.vv.personaddresszipcode;

import com.vv.personaddresszipcode.model.Address;
import com.vv.personaddresszipcode.model.Person;
import com.vv.personaddresszipcode.repo.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PersonAddressZipcodeApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger( PersonAddressZipcodeApplication.class );

    @Autowired
    private PersonRepository personRepository;

    public static void main( String[] args ) {
        SpringApplication.run( PersonAddressZipcodeApplication.class, args );
    }

    @Override
    public void run( String... args ) {
        // Insert default data
        Address address1 = new Address();
        address1.setStreet( "123 Main St" );
        address1.setCity( "City1" );
        address1.setZipCode( "12345" );

        Person person1 = new Person();
        person1.setName( "John Doe" );
        person1.setAddress( address1 );

        Address address2 = new Address();
        address2.setStreet( "456 Elm St" );
        address2.setCity( "City2" );
        address2.setZipCode( "67890" );

        Person person2 = new Person();
        person2.setName( "Jane Smith" );
        person2.setAddress( address2 );

        personRepository.save( person1 );
        personRepository.save( person2 );

        List<Person> result = personRepository.findByAddressZipCode( "67890" );
        result.forEach( p -> log.info( p.toString() ) );

        // IllegalArgumentException
        // List<Person> result2 = personRepository.findByAddressWithZipCode("67890");
        // result2.forEach(p -> log.error(p.toString()));

        List<Person> result3 = personRepository.findByAddress_ZipCode( "67890" );
        result3.forEach( p -> log.info( p.toString() ) );
    }
}
