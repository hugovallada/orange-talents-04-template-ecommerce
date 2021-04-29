package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank @Email String login;
    private @NotBlank @Size(min = 6) String senha;

    @CreationTimestamp
    private LocalDateTime instanteDeRegistro;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Funcao> funcoes = new ArrayList<>();

    /**
     *
     * @param login string no formato de email
     * @param senha string em texto limpo
     * @param encoder uma instância de BCryptEncoder que será utilizado para criptografar a senha limpa passada
     */
    public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha, BCryptPasswordEncoder encoder) {
        Assert.hasText(login, "O login não pode ser nulo ou em branco");
        Assert.notNull(senha, "A senha não pode ser nula");
        Assert.state(senha.length() >= 6, "A senha deve ter no mínimo 6 caracteres");

        this.login = login;
        this.senha = encoder.encode(senha);
    }

    /**
     * Construtor específico do JPA
     */
    @Deprecated
    public Usuario(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return funcoes;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

