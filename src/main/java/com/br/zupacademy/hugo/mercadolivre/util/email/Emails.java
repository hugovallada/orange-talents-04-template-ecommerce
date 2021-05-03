package com.br.zupacademy.hugo.mercadolivre.util.email;

import com.br.zupacademy.hugo.mercadolivre.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

@Component
public class Emails {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull Pergunta pergunta){
        mailer.send("<html>...</html>","Nova pergunta...","novapergunta@nossomercadolivre.com", pergunta.getUsuario().getUsername(), pergunta.getProduto().getUsuario().getUsername());
    }
}
