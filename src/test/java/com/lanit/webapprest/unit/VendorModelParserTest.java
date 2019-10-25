package com.lanit.webapprest.unit;

import com.github.javafaker.Faker;
import com.lanit.webapprest.web.request.car.VendorModelParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorModelParserTest {
    private final Faker faker = Faker.instance();
    @Autowired
    private VendorModelParser vendorModelParser;

    @Test
    public void testValidParser() {
        String vendor = faker.bothify("????");
        String carName = new StringBuilder(vendor).append('-').append(faker.bothify("????")).toString();

        vendorModelParser.parse(carName);

        assertEquals(vendor, vendorModelParser.getVendor());
        assertEquals(carName, vendorModelParser.getModel());
    }

    @Test
    public void testInvalidParser() {
        try {
            vendorModelParser.parse(faker.bothify("???????????"));
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            vendorModelParser.parse(faker.bothify("?????.??????"));
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            vendorModelParser.parse(faker.bothify("-?????-??????"));
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
}
