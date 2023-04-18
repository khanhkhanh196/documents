package com.restapi.restapidemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CodeMustStartWithConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeMustStartWith {
    public String value() default "LOVE";

    public String message() default "must start with LOVE";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
