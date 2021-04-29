package com.br.zupacademy.hugo.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CadastroCategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CadastroCategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @PostMapping
    public void criarNovaCategoria(@RequestBody @Valid NovaCategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
    }
}
