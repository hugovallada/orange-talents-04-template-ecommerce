package com.br.zupacademy.hugo.mercadolivre.produto.opiniao;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;

import javax.validation.constraints.*;

public class NovaOpiniaoRequest {

    @NotNull @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoProduto toModel(Produto produto, Usuario usuario){
        return new OpiniaoProduto(nota, titulo,descricao,produto, usuario);
    }
}
