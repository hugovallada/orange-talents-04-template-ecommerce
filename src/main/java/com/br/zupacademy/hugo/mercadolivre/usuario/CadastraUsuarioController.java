package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class CadastraUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void cadastrarAutores(@RequestBody @Valid UsuarioRequest usuarioRequest){
        usuarioRepository.save(usuarioRequest.toModel());
    }

}
