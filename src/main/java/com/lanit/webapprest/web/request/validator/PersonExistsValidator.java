package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class PersonExistsValidator implements ConstraintValidator<ValidPersonAge, Long> {
    private static final int MIN_AGE = 18;

    private final PersonRepositoryInterface personRepository;

    public PersonExistsValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Person> person = personRepository.findById(personId);

        return person.isPresent();
    }
}