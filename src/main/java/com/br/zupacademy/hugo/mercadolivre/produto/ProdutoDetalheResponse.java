package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.caracteristica.CaracteristicaResponse;
import com.br.zupacademy.hugo.mercadolivre.produto.imagem.ImagemResponse;
import com.br.zupacademy.hugo.mercadolivre.produto.opiniao.OpiniaoResponse;
import com.br.zupacademy.hugo.mercadolivre.produto.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDetalheResponse {

    private String nome;

    private BigDecimal preco;

    private String descricao;

    private Double media;

    private Integer totalDeOpinioes;

    private List<CaracteristicaResponse> caracteristicas = new ArrayList<>();

    private List<OpiniaoResponse> opinioes = new ArrayList<>();

    private List<PerguntaResponse> perguntas = new ArrayList<>();

    private List<ImagemResponse> imagens = new ArrayList<>();

    public ProdutoDetalheResponse(String nome, BigDecimal preco, String descricao,
                                  List<CaracteristicaResponse> caracteristicas, List<OpiniaoResponse> opinioes,
                                  List<PerguntaResponse> perguntas, List<ImagemResponse> imagens, Integer totalDeOpinioes,
                                  Double media) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.opinioes = opinioes;
        this.perguntas = perguntas;
        this.imagens = imagens;
        this.totalDeOpinioes = totalDeOpinioes;
        this.media = media;
    }

    public static ProdutoDetalheResponse toResponse(Produto produto) {
        return new ProdutoDetalheResponse(
                produto.getNome(),
                produto.getValor(),
                produto.getDescricao(),
                produto.getCaracteristicaProdutos().stream().map(CaracteristicaResponse::toResponse).collect(Collectors.toList()),
                produto.getOpinioesProdutos().stream().map(OpiniaoResponse::toResponse).collect(Collectors.toList()),
                produto.getPerguntas().stream().map(PerguntaResponse::toResponse).collect(Collectors.toList()),
                produto.getImagensProduto().stream().map(ImagemResponse::toResponse).collect(Collectors.toList()),
                produto.getTotalDeOpinioes(),
                produto.getMediaOpinioes()
        );
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
        return media;
    }

    public Integer getTotalDeOpinioes() {
        return totalDeOpinioes;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }
}
