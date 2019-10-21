package com.lanit.webapprest.web.request.validator;

import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.request.vo.Id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonNotExistsValidator implements ConstraintValidator<PersonNotExists, Id> {
    private final PersonRepositoryInterface personRepository;

    public PersonNotExistsValidator(PersonRepositoryInterface personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean isValid(Id personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId  == null || personId.getValue() == null) return false;

        return !personRepository.findById(personId.getValue()).isPresent();
    }
}