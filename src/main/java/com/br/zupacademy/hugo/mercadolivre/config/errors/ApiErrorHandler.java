package com.br.zupacademy.hugo.mercadolivre.config.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ApiErrorResponse> errors = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();

        fieldErrors.forEach(fieldError -> {
            errors.add(new ApiErrorResponse(fieldError.getField(),
                    messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())));
        });

        objectErrors.forEach(objectError -> {
            errors.add(new ApiErrorResponse(objectError.getObjectName(),
                    messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        });

        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ApiErrorResponse handleBadCredentialsException(BadCredentialsException exception){
        return new ApiErrorResponse("Login", "Senha ou usuário inválido");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiErrorResponse handleEntityNotFoundException(EntityNotFoundException exception){
        return new ApiErrorResponse("Objeto não encontrado", exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiErrorResponse handleAccessDeniedException(AccessDeniedException exception){
        return new ApiErrorResponse("PROIBIDO", exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ApiErrorResponse> handleBindException(BindException exception){
        List<ApiErrorResponse> errors = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();

        fieldErrors.forEach(fieldError -> {
            errors.add(new ApiErrorResponse(fieldError.getField(),
                    messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())));
        });

        objectErrors.forEach(objectError -> {
            errors.add(new ApiErrorResponse(objectError.getObjectName(),
                    messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        });


        return errors;
    }
}
