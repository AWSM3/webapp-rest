package com.lanit.webapprest.web.controller;

import com.lanit.webapprest.dto.CarDto;
import com.lanit.webapprest.dto.PersonWithCarsDto;
import com.lanit.webapprest.entity.Car;
import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.PersonSaveRequest;
import com.lanit.webapprest.web.request.validator.PersonExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {
    private final PersonRepositoryInterface personRepository;

    public PersonController(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/person")
    public ResponseEntity save(@Valid @RequestBody PersonSaveRequest request) {
        personRepository.save(
            new Person(request.getId(), request.getName(), request.getBirthdate())
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/personwithcars")
    @ResponseBody
    public ResponseEntity personWithCars(@PersonExists long personId) {
        Person person = personRepository.getOne(personId);
        List<CarDto> carDtoList = person.getCars().stream()
                .map(car -> new CarDto(car.getId(), car.getVendor(), car.getModel(), car.getHorsepower()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
            new PersonWithCarsDto(person.getId(), person.getName(), person.getBirthdate(), carDtoList)
        );
    }
}
