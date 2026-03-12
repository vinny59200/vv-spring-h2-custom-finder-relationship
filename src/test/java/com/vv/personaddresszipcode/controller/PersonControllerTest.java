package com.vv.personaddresszipcode.controller;

import com.vv.personaddresszipcode.model.Address;
import com.vv.personaddresszipcode.model.Person;
import com.vv.personaddresszipcode.repo.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( PersonController.class )
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    @DisplayName( "GET /persons/zipcode/{zipCode} should return persons matching the zip code" )
    void shouldReturnPersonsByZipCode() throws Exception {
        Address address1 = new Address( "1 Main Street", "Lille", "59000" );
        Person person1 = new Person( "Alice", address1 );

        Address address2 = new Address( "2 Second Street", "Lille", "59000" );
        Person person2 = new Person( "Bob", address2 );

        given( personRepository.findByAddressZipCode( "59000" ) )
                .willReturn( List.of( person1, person2 ) );

        mockMvc.perform( get( "/persons/zipcode/59000" ) )
               .andExpect( status().isOk() )
               .andExpect( jsonPath( "$[0].name" ).value( "Alice" ) )
               .andExpect( jsonPath( "$[0].address.street" ).value( "1 Main Street" ) )
               .andExpect( jsonPath( "$[0].address.city" ).value( "Lille" ) )
               .andExpect( jsonPath( "$[0].address.zipCode" ).value( "59000" ) )
               .andExpect( jsonPath( "$[1].name" ).value( "Bob" ) )
               .andExpect( jsonPath( "$[1].address.street" ).value( "2 Second Street" ) )
               .andExpect( jsonPath( "$[1].address.city" ).value( "Lille" ) )
               .andExpect( jsonPath( "$[1].address.zipCode" ).value( "59000" ) );
    }

    @Test
    @DisplayName( "GET /persons/zipcode/{zipCode} should return an empty list when no person matches" )
    void shouldReturnEmptyListWhenNoPersonMatches() throws Exception {
        given( personRepository.findByAddressZipCode( "99999" ) )
                .willReturn( List.of() );

        mockMvc.perform( get( "/persons/zipcode/99999" ) )
               .andExpect( status().isOk() )
               .andExpect( jsonPath( "$" ).isEmpty() );
    }
}