package com.br.zupacademy.hugo.mercadolivre.produto.pergunta;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.produto.ProdutoRepository;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.util.email.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private DisparadorDeEmail disparadorDeEmail;

    @Autowired
    private Emails emails;


    @PostMapping("/{idProduto}/pergunta")
    @Transactional
    public void cadastrarPergunta(@RequestBody @Valid NovaPerguntaRequest perguntaRequest,
                                  @PathVariable Long idProduto,
                                  @AuthenticationPrincipal Usuario usuario){

        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);

        if(produtoOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar um produto com id " + idProduto);

        Produto produto = produtoOptional.get();

        if(usuario.getId() == produto.getUsuario().getId()) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode enviar uma pergunta para o seu próprio produto");

        Pergunta pergunta = perguntaRequest.toModel(usuario, produto);
        produto.associarPergunta(pergunta);

        produtoRepository.save(produto);

        emails.novaPergunta(pergunta);
    }

}
