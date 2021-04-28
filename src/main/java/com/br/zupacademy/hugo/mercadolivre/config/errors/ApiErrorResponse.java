package com.br.zupacademy.hugo.mercadolivre.config.errors;

public class ApiErrorResponse {

    private String field;

    private String message;

    public ApiErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
