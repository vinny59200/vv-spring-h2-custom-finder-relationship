package com.vv.personaddresszipcode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String street;
    private String city;
    private String zipCode;

    // JPA needs a no-args constructor
    public Address() {
    }

    public Address( String street, String city, String zipCode ) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    // Getters / Setters
    public Long getId() {
        return id;
    }

    public void setId( Long id ) { // usually not used, but kept for completeness
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet( String street ) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode( String zipCode ) {
        this.zipCode = zipCode;
    }

    // equals / hashCode (typical JPA-safe approach: compare by id when present)
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

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

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
