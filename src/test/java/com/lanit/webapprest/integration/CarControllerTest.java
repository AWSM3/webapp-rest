package com.lanit.webapprest.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.controller.CarController;
import com.lanit.webapprest.web.request.CarSaveRequest;
import com.lanit.webapprest.web.request.car.VendorModelParser;
import com.lanit.webapprest.web.request.vo.Id;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {
    private Faker faker = Faker.instance();

    @MockBean
    private CarRepositoryInterface carRepository;
    @MockBean
    private PersonRepositoryInterface personRepository;
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private VendorModelParser vendorModelParser;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testValidCarSave() throws Exception {
        Person person = new Person()
                .setId(faker.number().randomNumber())
                .setName(faker.starTrek().character())
                .setBirthdate(LocalDate.now().minusYears(20));

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));

        CarSaveRequest request = new CarSaveRequest()
                .setId(new Id(faker.number().randomNumber()))
                .setModel(faker.bothify("????-????"))
                .setHorsepower(faker.number().randomDigitNotZero())
                .setOwnerId(new Id(person.getId()));

        mockMvc.perform(
                post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidCarSave() throws Exception {
        Person person = new Person()
                .setId(faker.number().randomNumber())
                .setName(faker.starTrek().character())
                .setBirthdate(LocalDate.now());

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));

        CarSaveRequest request = new CarSaveRequest()
                .setId(new Id(faker.number().randomNumber()))
                .setModel(faker.bothify("-????-????"))
                .setHorsepower(faker.number().randomDigitNotZero())
                .setOwnerId(new Id(person.getId()));

        mockMvc.perform(
                post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isBadRequest());
    }
}
