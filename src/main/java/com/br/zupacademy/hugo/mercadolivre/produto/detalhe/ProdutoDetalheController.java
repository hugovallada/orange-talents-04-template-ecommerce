package com.br.zupacademy.hugo.mercadolivre.produto.detalhe;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/produtos")
public class ProdutoDetalheController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDetalheResponse> detalharProduto(@PathVariable Long idProduto){
        Produto produto = produtoRepository
                .findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"O produto de id " + idProduto + " não foi encontrado"));

        return ResponseEntity.ok(new ProdutoDetalheResponse(produto));

    }
}
