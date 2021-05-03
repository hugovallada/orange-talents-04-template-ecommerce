package com.br.zupacademy.hugo.mercadolivre.util.email;

import com.br.zupacademy.hugo.mercadolivre.produto.pergunta.Pergunta;

public class DevEmailDePerguntas implements EmailDePerguntas{

    @Override
    public void enviarEmailNovaPergunta(Pergunta pergunta) {
        System.out.println("um novo email foi enviado para: " + pergunta.getProduto().getUsuario().getUsername());
    }
}
