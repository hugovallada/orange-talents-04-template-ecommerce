package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    private @NotBlank String titulo;

    public NovaPerguntaRequest(@JsonProperty("titulo") String titulo) {
        this.titulo = titulo;
    }
}
