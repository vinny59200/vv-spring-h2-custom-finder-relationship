package com.vv.personaddresszipcode.repo;

import com.vv.personaddresszipcode.model.Address;
import com.vv.personaddresszipcode.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName( "findByAddressZipCode should return only persons with the requested zip code" )
    void shouldFindPersonsByAddressZipCode() {
        Person alice = new Person( "Alice", new Address( "1 Main Street", "Lille", "59000" ) );
        Person bob = new Person( "Bob", new Address( "2 Second Street", "Lille", "59000" ) );
        Person charlie = new Person( "Charlie", new Address( "3 Third Street", "Paris", "75000" ) );

        personRepository.save( alice );
        personRepository.save( bob );
        personRepository.save( charlie );

        List<Person> result = personRepository.findByAddressZipCode( "59000" );

        assertThat( result ).hasSize( 2 );
        assertThat( result )
                .extracting( Person::getName )
                .containsExactlyInAnyOrder( "Alice", "Bob" );
    }

    @Test
    @DisplayName( "findByAddress_ZipCode should also return only persons with the requested zip code" )
    void shouldFindPersonsByAddressZipCodeUsingUnderscoreSyntax() {
        Person alice = new Person( "Alice", new Address( "1 Main Street", "Lille", "59000" ) );
        Person david = new Person( "David", new Address( "4 Fourth Street", "Roubaix", "59100" ) );

        personRepository.save( alice );
        personRepository.save( david );

        List<Person> result = personRepository.findByAddress_ZipCode( "59100" );

        assertThat( result ).hasSize( 1 );
        assertThat( result.get( 0 )
                          .getName() ).isEqualTo( "David" );
        assertThat( result.get( 0 )
                          .getAddress()
                          .getZipCode() ).isEqualTo( "59100" );
    }

    @Test
    @DisplayName( "findByAddressZipCode should return an empty list when no person matches" )
    void shouldReturnEmptyListWhenZipCodeDoesNotExist() {
        personRepository.deleteAll();

        Person alice = new Person( "Alice", new Address( "1 Main Street", "Lille", "59000" ) );
        personRepository.save( alice );

        List<Person> result = personRepository.findByAddressZipCode( "99999" );

        assertThat( result ).isEmpty();
    }
}