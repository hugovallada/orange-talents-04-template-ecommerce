package com.br.zupacademy.hugo.mercadolivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    /**
     * @deprecated Construtor de uso exclusivo da JPA
     */
    @Deprecated
    public Categoria() {
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public String getNome() {
        return nome;
    }
}
