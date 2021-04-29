package com.br.zupacademy.hugo.mercadolivre.usuario;

import com.br.zupacademy.hugo.mercadolivre.util.validator.UniqueValue;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Funcao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    public Funcao(String nome) {
        this.nome = nome;
    }

    /**
     * @deprecated Construtor de uso exclusivo da JPA
     */
    @Deprecated
    public Funcao() {
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
