package com.lanit.webapprest.web.request;

import com.lanit.webapprest.web.request.validator.PersonNotExists;
import com.lanit.webapprest.web.request.validator.ValidDate;
import com.lanit.webapprest.web.request.validator.ValidDateValidator;
import com.lanit.webapprest.web.request.vo.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonSaveRequest {
    @NotNull(groups = Id.class) @PersonNotExists
    private Id id;
    @NotNull
    private String name;
    @NotNull @ValidDate
    private String birthdate;

    public Id getId() {
        return id;
    }

    public PersonSaveRequest setId(Id id) {
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
        return LocalDate.parse(birthdate, DateTimeFormatter.ofPattern(ValidDateValidator.DATE_FORMAT));
    }

    public PersonSaveRequest setBirthdate(@ValidDate String birthdate) {
        this.birthdate = birthdate;

        return this;
    }
}
