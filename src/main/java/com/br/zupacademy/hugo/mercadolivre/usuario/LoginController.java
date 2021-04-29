package com.br.zupacademy.hugo.mercadolivre.usuario;

import com.br.zupacademy.hugo.mercadolivre.config.securirty.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping
    public String realizarLogin(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.toUsernamePasswordAuthToken();
        Authentication auth = authenticationManager.authenticate(dadosLogin);
        return tokenUtil.gerarToken(auth);
    }
}
