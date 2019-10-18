package com.lanit.webapprest.web.controller;

import com.lanit.webapprest.dto.Statistics;
import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class UtilsController {
    private final PersonRepositoryInterface personRepository;
    private final CarRepositoryInterface carRepository;

    public UtilsController(PersonRepositoryInterface personRepository, CarRepositoryInterface carRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/statistics")
    @ResponseBody
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ResponseEntity statistics() {
        Statistics statistics = new Statistics(personRepository.count(), carRepository.count(), carRepository.countUniqueVendors());

        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ResponseEntity clear() {
        personRepository.deleteAll();
        carRepository.deleteAll();

        return ResponseEntity.ok().build();
    }
}
