package com.lanit.webapprest.unit;

import com.github.javafaker.Faker;
import com.lanit.webapprest.web.controller.PersonController;
import com.lanit.webapprest.web.request.PersonSaveRequest;
import com.lanit.webapprest.web.request.validator.ValidDateValidator;
import com.lanit.webapprest.web.request.vo.Id;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {
    private final Faker faker = Faker.instance();

    @Autowired
    private PersonController personController;

    @Test
    public void testPersonSave() {
        assertEquals(runPersonSaveRequest(faker.number().randomNumber()).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testPersonWithCars() {
        long personId = faker.number().randomNumber();
        runPersonSaveRequest(personId);

        assertEquals(personController.personWithCars(personId).getStatusCode(), HttpStatus.OK);
        assertEquals(personController.personWithCars(faker.number().randomNumber()).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity runPersonSaveRequest(long personId) {
        PersonSaveRequest request = new PersonSaveRequest();
        request.setId(new Id(personId))
                .setName(faker.starTrek().character())
                .setBirthdate(LocalDate.now().format(DateTimeFormatter.ofPattern(ValidDateValidator.DATE_FORMAT)));

        return personController.save(request);
    }
}
