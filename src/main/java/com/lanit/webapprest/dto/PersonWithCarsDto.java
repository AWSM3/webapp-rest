package com.lanit.webapprest.dto;

import com.lanit.webapprest.entity.Car;

import java.time.LocalDate;
import java.util.List;

public class PersonWithCarsDto {
    private long id;
    private String name;
    private LocalDate birthdate;
    private List<CarDto> cars;

    public PersonWithCarsDto(long id, String name, LocalDate birthdate, List<CarDto> cars) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public List<CarDto> getCars() {
        return cars;
    }
}
