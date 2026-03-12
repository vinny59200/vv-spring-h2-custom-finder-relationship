package com.vv.personaddresszipcode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

// Marks this class as a JPA entity.
// It will be mapped to a database table.
@Entity
public class Address {

    // Primary key of the entity.
    @Id

    // Tells JPA to generate the ID automatically using the database identity strategy.
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Street name and number of the address.
    private String street;

    // City of the address.
    private String city;

    // ZIP code of the address.
    private String zipCode;

    // No-argument constructor required by JPA.
    public Address() {
    }

    // Convenient constructor to create an Address with its main fields.
    public Address( String street, String city, String zipCode ) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    // Returns the database ID of this address.
    public Long getId() {
        return id;
    }

    // Sets the ID manually.
    // In practice, this is usually managed by JPA.
    public void setId( Long id ) {
        this.id = id;
    }

    // Returns the street value.
    public String getStreet() {
        return street;
    }

    // Updates the street value.
    public void setStreet( String street ) {
        this.street = street;
    }

    // Returns the city value.
    public String getCity() {
        return city;
    }

    // Updates the city value.
    public void setCity( String city ) {
        this.city = city;
    }

    // Returns the ZIP code value.
    public String getZipCode() {
        return zipCode;
    }

    // Updates the ZIP code value.
    public void setZipCode( String zipCode ) {
        this.zipCode = zipCode;
    }

    // Compares two Address objects.
    // For JPA entities, equality is based on the ID when it is available.
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !(o instanceof Address other) ) {
            return false;
        }
        return id != null && Objects.equals( id, other.id );
    }

    // Returns a hash code consistent with the equals implementation.
    // Using the class hash code is a common JPA-safe pattern.
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // Returns a readable string representation of the object.
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}