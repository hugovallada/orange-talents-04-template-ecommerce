package com.br.zupacademy.hugo.mercadolivre.util.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidation implements ConstraintValidator<UniqueValue, String> {

    private String campo;
    private Class<?> targetClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        campo = constraintAnnotation.campo();
        targetClass = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 from " + targetClass.getName() + " where " + campo + " = :value");
        query.setParameter("value", value);
        List<?> listaResultado = query.getResultList();

        Assert.state(listaResultado.size() <= 1, "PROBLEMA...VOCÊ JÁ POSSUI CAMPOS DUPLICADOS");

        return listaResultado.isEmpty();
    }
}
