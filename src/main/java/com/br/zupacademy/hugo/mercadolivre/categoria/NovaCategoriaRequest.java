package com.br.zupacademy.hugo.mercadolivre.categoria;

import com.br.zupacademy.hugo.mercadolivre.util.validator.ExistsId;
import com.br.zupacademy.hugo.mercadolivre.util.validator.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(campo = "nome", targetClass = Categoria.class)
    private String nome;

    @ExistsId(campo = "id", targetClass = Categoria.class, aceitaNull = true)
    private Long categoriaId;

    public NovaCategoriaRequest(String nome, Long categoriaId) {
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public Categoria toModel(CategoriaRepository repository){
        Categoria categoria = new Categoria(nome);

        if(categoriaId != null) {
            Optional<Categoria> categoriaOpt = repository.findById(categoriaId);
            if(categoriaOpt.isPresent()){
                categoria.setCategoriaMae(categoriaOpt.get());
            }
        }

        return categoria;
    }


}
