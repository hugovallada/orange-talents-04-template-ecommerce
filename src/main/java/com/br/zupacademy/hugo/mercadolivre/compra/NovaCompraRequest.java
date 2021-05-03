package com.br.zupacademy.hugo.mercadolivre.compra;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import com.br.zupacademy.hugo.mercadolivre.util.validator.GatewayExists;
import com.br.zupacademy.hugo.mercadolivre.util.validator.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull
    @ExistsId(targetClass = Produto.class, campo = "id")
    private Long idProduto;

    @NotNull @Positive
    private Integer quantidade;

    @NotNull @GatewayExists
    private String gateway;

    public NovaCompraRequest(Long idProduto, Integer quantidade, String gateway) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Compra toModel(Produto produto, Usuario usuario){
        return new Compra(produto, usuario, produto.getValor(), quantidade, GatewayPagamento.valueOf(gateway));
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getGateway() {
        return gateway;
    }
}
