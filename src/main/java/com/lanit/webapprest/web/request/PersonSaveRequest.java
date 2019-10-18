package com.lanit.webapprest.web.request;

import com.lanit.webapprest.web.request.validator.PersonNotExists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class PersonSaveRequest {
    @NotNull @PersonNotExists
    private long id;
    @NotNull
    private String name;
    @NotNull @Past
    private LocalDate birthdate;

    public long getId() {
        return id;
    }

    public PersonSaveRequest setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonSaveRequest setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public PersonSaveRequest setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }
}
