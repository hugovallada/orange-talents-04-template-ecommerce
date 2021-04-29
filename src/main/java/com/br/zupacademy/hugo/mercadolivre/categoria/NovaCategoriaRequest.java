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

    /**
     * Acabei criando uma validação não necessária para o momento,
     * para aproveitar o código já criado, adicionei o campo aceitaNull ao validador.
     *
     * Por default, este atributo é falso, e a validação falhará caso nenhum valor seja passado.
     * Caso aceitaNull seja passado com valor null, a validação falhará apenas se um valor for passado
     * e não existir.
     *
     */
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
                categoria.setCategoria(categoriaOpt.get());
            }
        }

        return categoria;
    }


}
