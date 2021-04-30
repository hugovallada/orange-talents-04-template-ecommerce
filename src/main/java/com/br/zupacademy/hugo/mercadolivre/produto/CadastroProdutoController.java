package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.categoria.CategoriaRepository;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public void cadastrarProduto(@RequestBody @Valid NovoProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario){

        produtoRepository.save(produtoRequest.toModel(categoriaRepository, usuario));
    }
}
