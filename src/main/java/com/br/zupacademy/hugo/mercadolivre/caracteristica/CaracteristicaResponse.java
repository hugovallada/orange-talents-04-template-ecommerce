package com.br.zupacademy.hugo.mercadolivre.caracteristica;

public class CaracteristicaResponse {

    private Long id;

    private String nome;

    private String descricao;

    public CaracteristicaResponse(CaracteristicaProduto caracteristicaProduto){
        this.id = caracteristicaProduto.getId();
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
