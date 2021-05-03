package com.br.zupacademy.hugo.mercadolivre.compra;

import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @NotNull @Positive
    private BigDecimal valorDoProduto;

    @NotNull @Positive
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GatewayPagamento gateway;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status = Status.INICIADA;

    public Compra(Produto produto, Usuario usuario, BigDecimal valorDoProduto, Integer quantidade, GatewayPagamento gateway) {
        this.produto = produto;
        this.usuario = usuario;
        this.valorDoProduto = valorDoProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }
}
