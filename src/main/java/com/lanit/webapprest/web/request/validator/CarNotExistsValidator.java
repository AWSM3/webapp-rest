package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.web.request.vo.Id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarNotExistsValidator implements ConstraintValidator<CarNotExists, Id> {
    private final CarRepositoryInterface carRepository;

    public CarNotExistsValidator(CarRepositoryInterface carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public boolean isValid(Id carId, ConstraintValidatorContext constraintValidatorContext) {
        if (carId == null || carId.getValue() == null) return false;

        return !carRepository.findById(carId.getValue()).isPresent();
    }
}