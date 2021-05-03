package com.br.zupacademy.hugo.mercadolivre.compra;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.util.validator.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull
    @ExistsId(targetClass = Produto.class, campo = "id")
    private Long idProduto;

    @NotNull @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequest(Long idProduto, Integer quantidade, GatewayPagamento gateway) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Compra toModel(Produto produto, Usuario usuario){
        return new Compra(produto, usuario, produto.getValor(), quantidade,gateway);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
