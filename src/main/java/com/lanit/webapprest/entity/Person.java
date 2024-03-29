package com.lanit.webapprest.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    private long id;
    private String name;
    private LocalDate birthdate;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "owner",
            orphanRemoval = true
    )
    private List<Car> cars;

    public Person() {}

    public Person(long id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Person setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Person setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
