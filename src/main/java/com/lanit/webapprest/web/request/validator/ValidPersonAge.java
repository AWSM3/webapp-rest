package com.lanit.webapprest.web.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidPersonAgeValidator.class)
@Documented
public @interface ValidPersonAge {
    String message() default "Too young for driving";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
