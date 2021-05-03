package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaPerguntaRequest {

    private String titulo;

    public NovaPerguntaRequest(@JsonProperty("titulo") String titulo) {
        this.titulo = titulo;
    }
}
