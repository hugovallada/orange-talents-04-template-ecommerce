package com.br.zupacademy.hugo.mercadolivre.util.validator;


import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidation implements ConstraintValidator<ExistsId, Long> {

    private String campo;

    private Class<?> targetClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        campo = constraintAnnotation.campo();
        targetClass = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("Select 1 from " + targetClass.getName() + " where " + campo + "= :value");
        query.setParameter("value", value);
        List<?> listaResultado = query.getResultList();

        Assert.state(listaResultado.size() <= 1, "PROBLEMA; VOCÊ TEM ID REPETIDO");

        return listaResultado.size() == 1;
    }
}
