package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));

            return localDate.isBefore(LocalDate.now());
        } catch (Throwable e) {
            return false;
        }
    }
}