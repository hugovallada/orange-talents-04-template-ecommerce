package com.br.zupacademy.hugo.mercadolivre.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Uploader {

    List<String> salvarImagens(List<MultipartFile> arquivos);
}
