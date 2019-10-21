package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.entity.Person;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.vo.Id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PersonExistsValidator implements ConstraintValidator<PersonExists, Id> {
    private static final int MIN_AGE = 18;

    private final PersonRepositoryInterface personRepository;

    public PersonExistsValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isValid(Id personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId  == null || personId.getValue() == null) return false;
        Optional<Person> person = personRepository.findById(personId.getValue());

        return person.isPresent();
    }
}