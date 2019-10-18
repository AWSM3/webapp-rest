package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.repository.PersonRepositoryInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonNotExistsValidator implements ConstraintValidator<PersonNotExists, Long> {
    private final PersonRepositoryInterface personRepository;

    public PersonNotExistsValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext constraintValidatorContext) {
        return !personRepository.findById(personId).isPresent();
    }
}