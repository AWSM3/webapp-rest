package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class ValidPersonAgeValidator implements ConstraintValidator<ValidPersonAge, Long> {
    private static final int MIN_AGE = 18;

    private final PersonRepositoryInterface personRepository;

    public ValidPersonAgeValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext constraintValidatorContext) {
        Person person = personRepository.getOne(personId);

        return Period.between(person.getBirthdate(), LocalDate.now()).getYears() >= MIN_AGE;
    }
}