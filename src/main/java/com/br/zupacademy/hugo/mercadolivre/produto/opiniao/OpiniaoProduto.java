package com.br.zupacademy.hugo.mercadolivre.produto.opiniao;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    @NotNull @ManyToOne
    private Produto produto;

    @NotNull @ManyToOne
    private Usuario usuario;

    public OpiniaoProduto(Integer nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }


}
