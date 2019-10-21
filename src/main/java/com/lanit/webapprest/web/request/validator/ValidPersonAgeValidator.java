package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.vo.Id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class ValidPersonAgeValidator implements ConstraintValidator<ValidPersonAge, Id> {
    private static final int MIN_AGE = 18;

    private final PersonRepositoryInterface personRepository;

    public ValidPersonAgeValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isValid(Id personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId == null || personId.getValue() == null) return false;
        Optional<Person> personOrNot = personRepository.findById(personId.getValue());

        return personOrNot.isPresent() && Period.between(personOrNot.get().getBirthdate(), LocalDate.now()).getYears() >= MIN_AGE;
    }
}