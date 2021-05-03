package com.br.zupacademy.hugo.mercadolivre.produto.opiniao;

public class OpiniaoResponse {

    private Long id;

    private String titulo;

    private String descricao;

    private Integer nota;

    private String username;

    public OpiniaoResponse(Long id, String titulo, String descricao, Integer nota, String username) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.username = username;
    }

    public static OpiniaoResponse toResponse(OpiniaoProduto opiniaoProduto){
        return new OpiniaoResponse(
                opiniaoProduto.getId(),
                opiniaoProduto.getTitulo(),
                opiniaoProduto.getDescricao(),
                opiniaoProduto.getNota(),
                opiniaoProduto.getUsuario().getUsername()
        );
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
