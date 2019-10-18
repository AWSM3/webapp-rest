package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.repository.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarNotExistsValidator implements ConstraintValidator<CarNotExists, Long> {
    private final CarRepositoryInterface carRepository;

    public CarNotExistsValidator(CarRepositoryInterface carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public boolean isValid(Long carId, ConstraintValidatorContext constraintValidatorContext) {
        return !carRepository.findById(carId).isPresent();
    }
}