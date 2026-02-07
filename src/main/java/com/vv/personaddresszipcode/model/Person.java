package com.vv.personaddresszipcode.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    @OneToOne( cascade = CascadeType.ALL,
               orphanRemoval = true )
    @JoinColumn( name = "address_id" ) // optional but recommended to control FK column name
    private Address address;

    // JPA needs a no-args constructor
    public Person() {
    }

    public Person( String name, Address address ) {
        this.name = name;
        this.address = address;
    }

    // Getters / Setters
    public Long getId() {
        return id;
    }

    public void setId( Long id ) { // usually not used, but kept for completeness
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress( Address address ) {
        this.address = address;
    }

    // equals / hashCode (JPA-safe: compare by id when present)
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !(o instanceof Person other) ) {
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
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
