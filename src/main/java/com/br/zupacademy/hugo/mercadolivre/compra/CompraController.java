package com.br.zupacademy.hugo.mercadolivre.compra;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.produto.ProdutoRepository;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.util.email.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private Mailer mailer;

    @PostMapping
    @Transactional
    public String realizarCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest,
                                 @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriComponentsBuilder){

        // Não é necessário verificar se está presente, pois temos a validação @ExistsId
        Produto produto = produtoRepository.findById(novaCompraRequest.getIdProduto()).get();

        if(produto.getUsuario().getId() == usuario.getId()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode comprar o seu próprio produto");

        boolean temDisponivel = produto.abaterEstoque(novaCompraRequest.getQuantidade());

        if(!temDisponivel) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa quantidade de produtos não está disponível");

        Compra novaCompra = novaCompraRequest.toModel(produto, usuario);
        compraRepository.save(novaCompra);
        produtoRepository.save(produto);

        mailer.send("<html>...</html", "Nova compra", "info@nossomercadolivre", usuario.getUsername(), produto.getUsuario().getUsername());

        if(novaCompraRequest.getGateway().equals(GatewayPagamento.PAGSEGURO)){
            String urlRetornoPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .build(novaCompra.getId()).toString();

            return "paypal.com/" + novaCompra.getId()+"?redirectUrl="+urlRetornoPagSeguro;
        } else {
            String urlRetornoPayPall = uriComponentsBuilder.path("/retorno-paypall/{id}")
                    .build(novaCompra.getId()).toString();

            return "paypal.com/" + novaCompra.getId()+"?redirectUrl="+urlRetornoPayPall;
        }

    }
}
