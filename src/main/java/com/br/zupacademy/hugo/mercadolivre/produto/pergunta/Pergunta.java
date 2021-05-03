package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String titulo;

    private @CreationTimestamp LocalDateTime instanteDeRegistro;

    @ManyToOne
    private @NotNull Usuario usuario;

    @ManyToOne
    private @NotNull Produto produto;


    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    /**
     * @deprecated Construtor de uso exclusivo da JPa
     */
    @Deprecated
    public Pergunta() {
    }
}
