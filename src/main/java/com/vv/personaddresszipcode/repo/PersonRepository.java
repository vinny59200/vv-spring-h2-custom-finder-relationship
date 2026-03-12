package com.vv.personaddresszipcode.repo;

import com.vv.personaddresszipcode.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Marks this interface as a Spring Data repository.
// In many cases this annotation is optional because JpaRepository
// implementations are detected automatically by Spring.
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Custom derived query method.
    // Spring Data JPA interprets this method name as:
    // find all Person entities where person.address.zipCode = given zipCode
    List<Person> findByAddressZipCode( String zipCode );

    // This method name is invalid for Spring Data JPA query derivation.
    // "With" is not understood here as a property navigation keyword,
    // so it would throw an IllegalArgumentException at runtime.
    // List<Person> findByAddressWithZipCode(String zipCode);

    // Equivalent valid form using an underscore to make the nested
    // property navigation more explicit:
    // address -> zipCode
    List<Person> findByAddress_ZipCode( String zipCode );

    // This would not compile because '@' is not allowed in a Java method name.
    // List<Person> findByAddress@ZipCode(String zipCode);
}