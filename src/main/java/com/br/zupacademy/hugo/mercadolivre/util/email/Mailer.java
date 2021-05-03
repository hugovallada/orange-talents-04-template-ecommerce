package com.br.zupacademy.hugo.mercadolivre.util.email;

public interface Mailer {

    /**
     * @param body corpo do email
     * @param subject assunto do email
     * @param nameFrom nome para aparecer no provedor do email
     * @param from email de origem
     * @param to email de destino
     */
    void send(String body, String subject, String nameFrom, String from, String to);
}
