package com.vv.personaddresszipcode.repo;

import com.vv.personaddresszipcode.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//chatgpt assistance: https://chat.openai.com/share/f07b6c75-8955-4f59-b50d-5a87d17f7735
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByAddressZipCode(String zipCode);

    //List<Person> findByAddressWithZipCode(String zipCode); throws IllegalArgumentException

    List<Person> findByAddress_ZipCode(String zipCode);

    //List<Person> findByAddress@ZipCode(String zipCode); does not compile
}

