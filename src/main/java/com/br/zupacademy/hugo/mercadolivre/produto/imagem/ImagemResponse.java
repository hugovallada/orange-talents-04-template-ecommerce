package com.br.zupacademy.hugo.mercadolivre.produto.imagem;

public class ImagemResponse {

    private String link;

    public ImagemResponse(String link) {
        this.link = link;
    }

    public static ImagemResponse toResponse(ImagemProduto imagem) {
        return new ImagemResponse(imagem.getLink());
    }

    public String getLink() {
        return link;
    }
}
