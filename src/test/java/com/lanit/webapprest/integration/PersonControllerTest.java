package com.lanit.webapprest.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.lanit.webapprest.entity.Car;
import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.controller.PersonController;
import com.lanit.webapprest.web.request.PersonSaveRequest;
import com.lanit.webapprest.web.request.validator.ValidDateValidator;
import com.lanit.webapprest.web.request.vo.Id;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    private Faker faker = Faker.instance();

    @MockBean
    private PersonRepositoryInterface personRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testValidSave() throws Exception {
        Person person = new Person();
        person.setId(faker.number().randomNumber());

        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonSaveRequest request = new PersonSaveRequest()
                .setId(new Id(faker.number().randomNumber()))
                .setName(faker.starTrek().character())
                .setBirthdate(LocalDate.now().minusDays(10).format(DateTimeFormatter.ofPattern(ValidDateValidator.DATE_FORMAT)));

        makeSavePersonRequest(request)
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidSave() throws Exception {
        PersonSaveRequest request = new PersonSaveRequest()
                .setId(new Id(faker.number().randomNumber()))
                .setName(faker.starTrek().character())
                .setBirthdate(LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern(ValidDateValidator.DATE_FORMAT)));

        makeSavePersonRequest(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPersonWithCars() throws Exception {
        Person person = new Person()
                .setId(faker.number().randomNumber())
                .setName(faker.starTrek().character())
                .setBirthdate(LocalDate.now());
        Car car = new Car();
        car.setId(faker.number().randomNumber())
                .setModel(faker.bothify("????-????"))
                .setHorsepower(faker.number().randomDigitNotZero()).setOwner(person);
        List<Car> cars = new ArrayList<Car>();
        cars.add(car);
        person.setCars(cars);

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));

        mockMvc.perform(get("/personwithcars").param("personid", Long.toString(person.getId())))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(person.getId()));
    }

    private ResultActions makeSavePersonRequest(PersonSaveRequest request) throws Exception {
        return mockMvc.perform(
                post("/person")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }
}
