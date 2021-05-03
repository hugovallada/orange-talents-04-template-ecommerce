package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PerguntaResponse {

    private Long id;

    private String titulo;

    private String username;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataPergunta;

    public PerguntaResponse(Long id, String titulo, String username, LocalDateTime dataPergunta) {
        this.id = id;
        this.titulo = titulo;
        this.username = username;
        this.dataPergunta = dataPergunta;
    }

    public static PerguntaResponse toResponse(Pergunta pergunta){
        return new PerguntaResponse(
                pergunta.getId(),
                pergunta.getTitulo(),
                pergunta.getUsuario().getUsername(),
                pergunta.getInstanteDeRegistro()
        );
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
