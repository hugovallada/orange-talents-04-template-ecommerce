package com.br.zupacademy.hugo.mercadolivre.produto.detalhe;

import com.br.zupacademy.hugo.mercadolivre.caracteristica.CaracteristicaResponse;
import com.br.zupacademy.hugo.mercadolivre.produto.Produto;
import com.br.zupacademy.hugo.mercadolivre.produto.imagem.ImagemResponse;
import com.br.zupacademy.hugo.mercadolivre.produto.opiniao.OpiniaoResponse;
import com.br.zupacademy.hugo.mercadolivre.produto.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class ProdutoDetalheResponse {

    private String nome;

    private BigDecimal preco;

    private String descricao;

    private Double media;

    private Integer totalDeOpinioes;

    private Set<CaracteristicaResponse> caracteristicas = new HashSet<>();

    private Set<OpiniaoResponse> opinioes = new HashSet<>();

    private Set<PerguntaResponse> perguntas = new HashSet<>();

    private List<ImagemResponse> imagens = new ArrayList<>();

    public ProdutoDetalheResponse(Produto produto){
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.getCaracteristicaProdutos().stream().map(CaracteristicaResponse::new).collect(Collectors.toSet());
        this.opinioes = produto.getOpinioesProdutos().stream().map(OpiniaoResponse::new).collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas().stream().map(PerguntaResponse::new).collect(Collectors.toSet());
        this.imagens = produto.getImagensProduto().stream().map(ImagemResponse::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMedia() {
        DoubleStream nota = opinioes.stream().mapToDouble(opiniao -> opiniao.getNota());
        return nota.average().orElseGet(()-> 0.0);
    }

    public Integer getTotalDeOpinioes() {
        return opinioes.size();
    }

    public Set<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }
}
