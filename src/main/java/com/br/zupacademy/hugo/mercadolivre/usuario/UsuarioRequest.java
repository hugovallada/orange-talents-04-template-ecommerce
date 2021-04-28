package com.br.zupacademy.hugo.mercadolivre.usuario;

import com.br.zupacademy.hugo.mercadolivre.util.validator.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank @Email
    @UniqueValue(campo = "login", targetClass = Usuario.class)
    private String login;

    @NotBlank @Size(min = 6)
    private String senhaLimpa;

    public UsuarioRequest(String login, String senhaLimpa) {
        this.login = login;
        this.senhaLimpa = senhaLimpa;
    }

    public Usuario toModel(BCryptPasswordEncoder encoder){
        return new Usuario(login, senhaLimpa, encoder);
    }
}
