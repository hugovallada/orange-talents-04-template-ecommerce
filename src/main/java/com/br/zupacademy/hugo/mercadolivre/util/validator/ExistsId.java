package com.br.zupacademy.hugo.mercadolivre.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsIdValidation.class)
public @interface ExistsId {

    Class<?> targetClass();
    String campo();
    boolean aceitaNull() default false;
    String message() default "O campo não existe";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
