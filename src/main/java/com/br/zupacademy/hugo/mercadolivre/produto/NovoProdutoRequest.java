package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.caracteristica.NovaCaracteristicaRequest;
import com.br.zupacademy.hugo.mercadolivre.categoria.Categoria;
import com.br.zupacademy.hugo.mercadolivre.categoria.CategoriaRepository;
import com.br.zupacademy.hugo.mercadolivre.util.validator.ExistsId;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class NovoProdutoRequest {

    private @NotBlank String nome;

    private @Positive @NotNull BigDecimal valor;

    private @PositiveOrZero @NotNull  Integer quantidade;

    @NotNull @Size(min = 3)
    private Set<NovaCaracteristicaRequest> caracteristicas = new HashSet<>();

    private @NotBlank @Size(max = 1000) String descricao;

    @NotNull @ExistsId(targetClass = Categoria.class, campo = "id")
    private Long categoriaId;

    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, Set<NovaCaracteristicaRequest> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository){
       @NotNull Categoria categoria =  categoriaRepository.findById(categoriaId).get();

       return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria);

    }
}
