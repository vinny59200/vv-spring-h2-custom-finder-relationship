package com.vv.personaddresszipcode.model;

import jakarta.persistence.*;

import java.util.Objects;

// Marks this class as a JPA entity.
// It will be mapped to a database table.
@Entity
public class Person {

    // Primary key of the entity.
    @Id

    // Tells JPA to generate the ID automatically using the database identity strategy.
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Name of the person.
    private String name;

    // Defines a one-to-one relationship between Person and Address.
    // cascade = ALL means operations on Person (persist, remove, etc.)
    // are also applied to the linked Address.
    // orphanRemoval = true means that if the Address is no longer linked
    // to this Person, it can be removed from the database.
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    // Specifies the foreign key column name in the Person table.
    @JoinColumn( name = "address_id" )
    private Address address;

    // No-argument constructor required by JPA.
    public Person() {
    }

    // Convenient constructor to create a Person with a name and an address.
    public Person( String name, Address address ) {
        this.name = name;
        this.address = address;
    }

    // Returns the database ID of this person.
    public Long getId() {
        return id;
    }

    // Sets the ID manually.
    // In practice, this is usually managed by JPA.
    public void setId( Long id ) {
        this.id = id;
    }

    // Returns the person's name.
    public String getName() {
        return name;
    }

    // Updates the person's name.
    public void setName( String name ) {
        this.name = name;
    }

    // Returns the associated address.
    public Address getAddress() {
        return address;
    }

    // Updates the associated address.
    public void setAddress( Address address ) {
        this.address = address;
    }

    // Compares two Person objects.
    // For JPA entities, equality is based on the ID when it is available.
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

    // Returns a hash code consistent with the equals implementation.
    // Using the class hash code is a common JPA-safe pattern.
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // Returns a readable string representation of the object.
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}