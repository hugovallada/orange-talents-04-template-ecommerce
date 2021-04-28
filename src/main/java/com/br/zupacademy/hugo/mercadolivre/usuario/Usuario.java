package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank @Email String login;
    private @NotBlank @Size(min = 6) String senha;

    @CreationTimestamp
    private LocalDateTime instanteDeRegistro;

    /**
     *
     * @param login string no formato de email
     * @param senha string em texto limpo
     * @param encoder uma instância de BCryptEncoder que será utilizado para criptografar a senha limpa passada
     */
    public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha, BCryptPasswordEncoder encoder) {
        this.login = login;
        this.senha = encoder.encode(senha);
    }

    /**
     * Construtor específico do JPA
     */
    @Deprecated
    public Usuario(){}
}

