package com.br.zupacademy.hugo.mercadolivre.caracteristica;

public class CaracteristicaResponse {

    private Long id;

    private String nome;

    private String descricao;

    public CaracteristicaResponse(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static CaracteristicaResponse toResponse(CaracteristicaProduto caracteristicaProduto){
        return new CaracteristicaResponse(
                caracteristicaProduto.getId(),
                caracteristicaProduto.getNome(),
                caracteristicaProduto.getDescricao()
        );
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
