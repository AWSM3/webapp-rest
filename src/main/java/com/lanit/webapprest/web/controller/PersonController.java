package com.lanit.webapprest.web.controller;

import com.lanit.webapprest.dto.CarDto;
import com.lanit.webapprest.dto.PersonWithCarsDto;
import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.PersonSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
            new Person(
                request.getId().getValue(),
                request.getName(),
                request.getBirthdate()
            )
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/personwithcars")
    @ResponseBody
    @Transactional
    public ResponseEntity personWithCars(@RequestParam Long personid) {
        Person person;
        Optional<Person> personOrNot = personRepository.findById(personid);
        if (!personOrNot.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        person = personOrNot.get();

        List<CarDto> carDtoList = person.getCars().stream()
                .map(car -> new CarDto(car.getId(), car.getVendor(), car.getModel(), car.getHorsepower()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
            new PersonWithCarsDto(
                    person.getId(),
                    person.getName(),
                    person.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    carDtoList
            )
        );
    }
}
