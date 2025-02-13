package com.br.zupacademy.hugo.mercadolivre.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NovaImagemRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();


    public NovaImagemRequest(@NotNull @Size(min=1) List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
