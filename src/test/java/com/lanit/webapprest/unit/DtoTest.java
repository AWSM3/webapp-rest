package com.lanit.webapprest.unit;

import com.github.javafaker.Faker;
import com.lanit.webapprest.dto.CarDto;
import com.lanit.webapprest.dto.PersonWithCarsDto;
import com.lanit.webapprest.dto.Statistics;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DtoTest {
    private final Faker faker = Faker.instance();

    @Test
    public void testCarDto() {
        long id = faker.number().randomNumber();
        String vendor = faker.bothify("????");
        String model = new StringBuilder(vendor).append("-").append(faker.bothify("??????")).toString();
        int horsePower = faker.number().randomDigitNotZero();

        CarDto dto = new CarDto(id, vendor, model, horsePower);

        assertEquals(dto.getId(), id);
        assertEquals(dto.getVendor(), vendor);
        assertEquals(dto.getModel(), model);
        assertEquals(dto.getHorsepower(), horsePower);
    }

    @Test
    public void personWithCarsDto() {
        long id = faker.number().randomNumber();
        String name = faker.starTrek().character();
        String birthdate = faker.date().birthday().toString();
        int carsCount = 5;

        List<CarDto> cars = new ArrayList();
        for (int i = 0; i < carsCount; i++) {
            long carId = faker.number().randomNumber();
            String carVendor = faker.bothify("????");
            String carModel = new StringBuilder(carVendor).append("-").append(faker.bothify("??????")).toString();
            int carHorsePower = faker.number().randomDigitNotZero();

            cars.add(new CarDto(carId, carVendor, carModel, carHorsePower));
        }

        PersonWithCarsDto dto = new PersonWithCarsDto(id, name, birthdate, cars);

        assertEquals(dto.getId(), id);
        assertEquals(dto.getName(), name);
        assertEquals(dto.getBirthdate(), birthdate);
        assertEquals(dto.getCars().size(), carsCount);
    }

    @Test
    public void testStatisticsDto() {
        int personCount = faker.number().randomDigitNotZero();
        int carCount = faker.number().randomDigitNotZero();
        int uniqueVendorCount = faker.number().randomDigitNotZero();
        Statistics dto = new Statistics(personCount, carCount, uniqueVendorCount);

        assertEquals(dto.getPersonCount(), personCount);
        assertEquals(dto.getCarCount(), carCount);
        assertEquals(dto.getUniqueVendorCount(), uniqueVendorCount);
    }
}
