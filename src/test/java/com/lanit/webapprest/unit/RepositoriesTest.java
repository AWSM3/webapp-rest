package com.lanit.webapprest.unit;

import com.github.javafaker.Faker;
import com.lanit.webapprest.entity.Car;
import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.car.VendorModelParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesTest {
    private final Faker faker = Faker.instance();

    @Autowired
    private PersonRepositoryInterface personRepository;
    @Autowired
    private CarRepositoryInterface carRepository;

    @Test
    public void testPersonRepository() {
        Person personOne = new Person(faker.number().randomNumber(), faker.starTrek().character(), LocalDate.now());
        Person personTwo = new Person(faker.number().randomNumber(), faker.starTrek().character(), LocalDate.now());
        personRepository.save(personOne);
        personRepository.save(personTwo);

        assertEquals(personRepository.getOne(personOne.getId()), personOne);
        assertEquals(personRepository.count(), 2);
    }

    @Test
    public void testCarRepository() {
        Person owner = new Person(faker.number().randomNumber(), faker.starTrek().character(), LocalDate.now());
        personRepository.save(owner);
        Car carOne = new Car(
                faker.number().randomNumber(),
                faker.bothify("????"),
                faker.bothify("????"),
                faker.number().randomDigitNotZero(),
                owner
        );
        Car carTwo = new Car(
                faker.number().randomNumber(),
                faker.bothify("????"),
                faker.bothify("????"),
                faker.number().randomDigitNotZero(),
                owner
        );
        carRepository.save(carOne);
        carRepository.save(carTwo);

        assertEquals(carRepository.getOne(carOne.getId()), carOne);
        assertEquals(carRepository.countPersonCars(owner), 2);
    }
}
