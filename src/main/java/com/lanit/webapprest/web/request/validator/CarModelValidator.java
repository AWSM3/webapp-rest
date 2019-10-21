package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.web.request.car.VendorModelParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarModelValidator implements ConstraintValidator<CarModel, String> {
    private final VendorModelParser vendorModelParser;

    public CarModelValidator(VendorModelParser vendorModelParser) {
        this.vendorModelParser = vendorModelParser;
    }

    @Override
    public boolean isValid(String model, ConstraintValidatorContext constraintValidatorContext) {
        try {
            vendorModelParser.parse(model);
        } catch (Throwable e) {
            return false;
        }

        return true;
    }
}