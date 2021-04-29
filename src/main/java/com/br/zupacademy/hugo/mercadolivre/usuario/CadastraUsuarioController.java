package com.br.zupacademy.hugo.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class CadastraUsuarioController {

   private final UsuarioRepository usuarioRepository;

   private final BCryptPasswordEncoder encoder;

   @Autowired
    public CadastraUsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(code = HttpStatus.CREATED)
    public void cadastrarAutores(@RequestBody @Valid UsuarioRequest usuarioRequest){
        usuarioRepository.save(usuarioRequest.toModel(encoder));
    }

}
