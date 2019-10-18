package com.lanit.webapprest.web.controller;

import com.lanit.webapprest.entity.Car;
import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.car.VendorModelParser;
import com.lanit.webapprest.web.request.CarSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CarController {
    private final VendorModelParser vendorModelParser;
    private final CarRepositoryInterface carRepository;
    private final PersonRepositoryInterface personRepository;

    public CarController(VendorModelParser vendorModelParser, CarRepositoryInterface carRepository, PersonRepositoryInterface personRepository) {
        this.vendorModelParser = vendorModelParser;
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("/car")
    public ResponseEntity save(@Valid @RequestBody CarSaveRequest request) {
        vendorModelParser.parse(request.getModel());
        String model = vendorModelParser.getModel();
        String vendor = vendorModelParser.getVendor();
        Car car = new Car(
                request.getId(),
                vendor,
                model,
                request.getHorsepower(),
                personRepository.getOne(request.getOwnerId())
        );

        carRepository.saveAndFlush(car);

        return ResponseEntity.ok().build();
    }
}
