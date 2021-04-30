package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.caracteristica.NovaCaracteristicaRequest;
import com.br.zupacademy.hugo.mercadolivre.categoria.Categoria;
import com.br.zupacademy.hugo.mercadolivre.categoria.CategoriaRepository;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.util.validator.ExistsId;
import io.jsonwebtoken.lang.Assert;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class NovoProdutoRequest {

    private @NotBlank String nome;

    private @Positive @NotNull BigDecimal valor;

    private @PositiveOrZero @NotNull  Integer quantidade;

    @NotNull @Size(min = 3) @Valid
    private Set<NovaCaracteristicaRequest> caracteristicas = new HashSet<>();

    private @NotBlank @Size(max = 1000) String descricao;

    @NotNull @ExistsId(targetClass = Categoria.class, campo = "id")
    private Long categoriaId;


    public NovoProdutoRequest(
            @NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Integer quantidade,
            @NotNull @Size(min = 3) @Valid Set<NovaCaracteristicaRequest> caracteristicas,
            @NotBlank @Size(max = 1000) String descricao, @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario){
       @NotNull Categoria categoria =  categoriaRepository.findById(categoriaId).get();

        Assert.notNull(categoria, "A categoria não pode ser nula");
        Assert.notNull(usuario, "O usuário não deve ser nulo");
       return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria, usuario);

    }

    public Set<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }
}
