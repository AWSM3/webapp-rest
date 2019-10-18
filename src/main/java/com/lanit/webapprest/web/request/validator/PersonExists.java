package com.lanit.webapprest.web.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PersonExistsValidator.class)
@Documented
public @interface PersonExists {
    String message() default "Person does not exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
