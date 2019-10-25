package com.lanit.webapprest.unit;

import com.github.javafaker.Faker;
import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.controller.CarController;
import com.lanit.webapprest.web.controller.PersonController;
import com.lanit.webapprest.web.request.CarSaveRequest;
import com.lanit.webapprest.web.request.PersonSaveRequest;
import com.lanit.webapprest.web.request.validator.ValidDateValidator;
import com.lanit.webapprest.web.request.vo.Id;
import com.mysql.cj.xdevapi.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarControllerTest {
    private Faker faker = Faker.instance();

    @Autowired
    private CarController carController;
    @Autowired
    private PersonRepositoryInterface personRepository;


    @Test
    public void testValidCarSave() {
        Person owner = makePerson();
        CarSaveRequest request = new CarSaveRequest();
        request.setId(new Id(faker.number().randomNumber()))
                .setModel(faker.bothify("????-????"))
                .setHorsepower(faker.number().randomDigitNotZero())
                .setOwnerId(new Id(owner.getId()));

        assertEquals(carController.save(request).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testInvalidCarSave() {
        Person owner = makePerson();
        CarSaveRequest request = new CarSaveRequest();
        request.setId(new Id(faker.number().randomNumber()))
                .setModel(faker.bothify("-????"))
                .setHorsepower(faker.number().randomDigitNotZero())
                .setOwnerId(new Id(owner.getId()));

        try {
            carController.save(request).getStatusCode();
            Assert.fail("Expected exception");
        } catch (Exception e) {}
    }

    private Person makePerson() {
        Person person = new Person(faker.number().randomNumber(), faker.starTrek().character(), LocalDate.now());
        personRepository.save(person);

        return person;
    }
}
