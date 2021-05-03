package com.br.zupacademy.hugo.mercadolivre.produto.opiniao;

public class OpiniaoResponse {

    private Long id;

    private String titulo;

    private String descricao;

    private Integer nota;

    private String username;

    public OpiniaoResponse(OpiniaoProduto opiniaoProduto){
        this.id = opiniaoProduto.getId();;
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
        this.nota = opiniaoProduto.getNota();
        this.username = opiniaoProduto.getUsuario().getUsername();
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public String getUsername() {
        return username;
    }
}
