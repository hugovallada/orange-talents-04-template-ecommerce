package com.br.zupacademy.hugo.mercadolivre.caracteristica;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NovaCaracteristicaRequest {

    private @NotBlank String nome;

    private @NotBlank String descricao;

    public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NovaCaracteristicaRequest that = (NovaCaracteristicaRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public CaracteristicaProduto toModel(Produto produto){
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
