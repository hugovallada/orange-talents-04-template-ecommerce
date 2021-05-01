package com.br.zupacademy.hugo.mercadolivre.produto.imagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DevImagemUploader implements Uploader{
    @Override
    public List<String> salvarImagens(List<MultipartFile> arquivos) {

        return arquivos.stream().map(arquivo -> "http://image-saver.academy.io/"+
                arquivo.getOriginalFilename()).collect(Collectors.toList());

    }
}
