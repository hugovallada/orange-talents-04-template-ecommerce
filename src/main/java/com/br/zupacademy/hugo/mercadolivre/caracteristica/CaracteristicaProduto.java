package com.br.zupacademy.hugo.mercadolivre.caracteristica;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Valid
    @NotNull
    @ManyToOne
    Produto produto;

    public CaracteristicaProduto(@NotBlank String nome,@NotBlank String descricao,@Valid @NotNull Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    /**
     * @deprecated Construtor de uso exclusivo da JPA
     */
    @Deprecated
    public CaracteristicaProduto() {
    }
}
