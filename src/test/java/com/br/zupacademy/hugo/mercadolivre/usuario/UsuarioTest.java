package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioTest {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @DisplayName("Senha de entrada nunca deve ser igual a senha salva, pois a senha salva deve estar criptografada")
    @ParameterizedTest
    @ValueSource(strings = {"123456788", "daudh7gtdhghdad8adhdaw", "8d8uwd20kdjadayhdha", "09283774dhdmkdndycyca"})
    void novoUsuarioDeveTerORetornoDeGetPasswordDiferenteDaSenhaDeEntrada(String input){
        Usuario usuario = new Usuario("hugolopes@gmail.com", input, encoder);
        Assertions.assertNotEquals(input, usuario.getPassword());
        Assertions.assertEquals("hugolopes@gmail.com", usuario.getUsername());
    }

    @Test
    @DisplayName("Não deve ser criado um novo usuário caso a senha ou o login sejam inválidos")
    void umaIllegalArgumentExceptionDeveSerLancadaCasoUmValorSejaInválido(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("hugolopes@gmail.com", null, encoder);
        });

        Assertions.assertThrows(IllegalStateException.class, () -> {
            Usuario usuario = new Usuario("hugolopes@gmail.com", "123", encoder);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario(null, "1234567", encoder);
        });
    }
}
