package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

    private @NotBlank @Email String login;

    private @NotBlank @Size(min = 6) String senhaLimpa;

    /**
     *
     * @param login
     * @param senhaLimpa A senha recebida, não pode ter sido previamente criptografada
     */
    public LoginRequest(String login, String senhaLimpa) {
        this.login = login;
        this.senhaLimpa = senhaLimpa;
    }

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthToken(){
        return new UsernamePasswordAuthenticationToken(login, senhaLimpa);
    }
}
