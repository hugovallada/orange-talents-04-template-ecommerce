package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    private @NotBlank String titulo;

    public NovaPerguntaRequest(@JsonProperty("titulo") String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(titulo, usuario, produto);
    }
}
