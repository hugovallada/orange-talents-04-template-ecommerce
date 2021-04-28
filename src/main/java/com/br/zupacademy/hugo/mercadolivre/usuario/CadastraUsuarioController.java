package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class CadastraUsuarioController {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void cadastrarAutores(){

    }

}
