package com.lanit.webapprest.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    private long id;
    private String vendor;
    private String model;
    private int horsepower;
    @OneToOne(fetch = FetchType.LAZY)
    private Person owner;

    public Car() {}

    public Car(long id, String vendor, String model, int horsepower, Person owner) {
        this.id = id;
        this.vendor = vendor;
        this.model = model;
        this.horsepower = horsepower;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public Car setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public Car setHorsepower(int horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public Person getOwner() {
        return owner;
    }

    public Car setOwner(Person owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
