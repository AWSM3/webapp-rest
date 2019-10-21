package com.lanit.webapprest.web.request;

import com.lanit.webapprest.web.request.validator.CarModel;
import com.lanit.webapprest.web.request.validator.CarNotExists;
import com.lanit.webapprest.web.request.validator.PersonExists;
import com.lanit.webapprest.web.request.validator.ValidPersonAge;
import com.lanit.webapprest.web.request.vo.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarSaveRequest {
    @NotNull @CarNotExists
    private Id id;
    @NotNull @NotBlank @CarModel
    private String model;
    @NotNull @Min(1)
    private int horsepower;
    @NotNull @PersonExists @ValidPersonAge
    private Id ownerId;

    public Id getId() {
        return id;
    }

    public CarSaveRequest setId(Id id) {
        this.id = id;
        return this;
    }


    public String getModel() {
        return model;
    }

    public CarSaveRequest setModel(String model) {
        this.model = model;

        return this;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public CarSaveRequest setHorsepower(int horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public Id getOwnerId() {
        return ownerId;
    }

    public CarSaveRequest setOwnerId(Id ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
