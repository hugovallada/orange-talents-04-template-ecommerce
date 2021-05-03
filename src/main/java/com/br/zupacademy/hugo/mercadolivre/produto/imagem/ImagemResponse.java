package com.br.zupacademy.hugo.mercadolivre.produto.imagem;

public class ImagemResponse {

    private String link;

    public ImagemResponse(ImagemProduto imagem){
        this.link = imagem.getLink();
    }



    public String getLink() {
        return link;
    }
}
