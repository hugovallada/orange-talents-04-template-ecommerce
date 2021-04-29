package com.br.zupacademy.hugo.mercadolivre.categoria;

import com.br.zupacademy.hugo.mercadolivre.util.validator.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(campo = "nome", targetClass = Categoria.class)
    private String nome;

    @NotNull
    private Long categoriaId;

    public NovaCategoriaRequest(String nome, Long categoriaId) {
        this.nome = nome;
        this.categoriaId = categoriaId;
    }


}
