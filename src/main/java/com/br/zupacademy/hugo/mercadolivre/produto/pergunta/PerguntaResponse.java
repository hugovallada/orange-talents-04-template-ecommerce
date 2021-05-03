package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PerguntaResponse {

    private Long id;

    private String titulo;

    private String username;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataPergunta;



    public PerguntaResponse(Pergunta pergunta){
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.username = pergunta.getUsuario().getUsername();
        this.dataPergunta = pergunta.getInstanteDeRegistro();
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDataPergunta() {
        return dataPergunta;
    }
}
