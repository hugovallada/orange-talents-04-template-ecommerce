package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.categoria.CategoriaRepository;
import com.br.zupacademy.hugo.mercadolivre.produto.imagem.NovaImagemRequest;
import com.br.zupacademy.hugo.mercadolivre.produto.imagem.Uploader;
import com.br.zupacademy.hugo.mercadolivre.produto.opiniao.NovaOpiniaoRequest;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.util.validator.ExistsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private Uploader uploader;

    @PostMapping
    @Transactional
    public void cadastrarProduto(@RequestBody @Valid NovoProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {

        produtoRepository.save(produtoRequest.toModel(categoriaRepository, usuario));
    }

    @PostMapping(value = "/{idProduto}/imagens")
    @Transactional
    public ResponseEntity<Void> adicionarImagens(@Valid NovaImagemRequest imagens,
                                                 @AuthenticationPrincipal Usuario usuario,
                                                 @PathVariable Long idProduto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();

            if (produto.getUsuario().getId() != usuario.getId())
                throw new AccessDeniedException("Você não tem permissão para modificar este produto");

            var links = uploader.salvarImagens(imagens.getImagens());

            produto.associarImagens(links);
            produtoRepository.save(produto);

            return ResponseEntity.ok().build();
        }

        throw new EntityNotFoundException("Não foi possível encontrar um produto com id " + idProduto);

    }

    @PostMapping("/{idProduto}/opiniao")
    @Transactional
    public void cadastrarOpiniao(@RequestBody @Valid NovaOpiniaoRequest opiniaoRequest,
                                 @PathVariable Long idProduto,
                                 @AuthenticationPrincipal Usuario usuario){

    }
}
