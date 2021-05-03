package com.br.zupacademy.hugo.mercadolivre.util.validator;

import com.br.zupacademy.hugo.mercadolivre.compra.GatewayPagamento;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GatewayExistsValidation implements ConstraintValidator<GatewayExists, String> {

    @Override
    public void initialize(GatewayExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for(var gateway : GatewayPagamento.values()){
            if(gateway.toString().equals(value)){
                return true;
            }
        }

        return false;
    }
}
