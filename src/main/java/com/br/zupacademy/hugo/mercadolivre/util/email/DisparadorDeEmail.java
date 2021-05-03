package com.br.zupacademy.hugo.mercadolivre.util.email;

import com.br.zupacademy.hugo.mercadolivre.produto.pergunta.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class DisparadorDeEmail {

    public void dispararEmailDeNovaPergunta(String destinatario, String assunto, EmailDePerguntas emailDePerguntas, Pergunta pergunta){
        System.out.println("Destinatário: " + destinatario);
        System.out.println("Assunto: " + assunto);
        emailDePerguntas.enviarEmailNovaPergunta(pergunta);
    }
}
