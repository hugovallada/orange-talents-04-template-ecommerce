package com.br.zupacademy.hugo.mercadolivre.produto.imagem;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String link;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    public ImagemProduto(@NotBlank @URL String link, @NotNull @Valid Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    /**
     * @deprecated Cosntrutor de uso exclusivo do Jpa
     */
    @Deprecated
    public ImagemProduto() {
    }

    public String getLink() {
        return link;
    }

    public Long getId() {
        return id;
    }
}
