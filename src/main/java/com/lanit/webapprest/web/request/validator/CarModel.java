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
@Constraint(validatedBy = CarModelValidator.class)
@Documented
public @interface CarModel {
    String message() default "Invalid car model name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
