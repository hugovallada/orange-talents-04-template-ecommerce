package com.br.zupacademy.hugo.mercadolivre.categoria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Optional;


public class NovaCategoriaRequestTest {

    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository = Mockito.mock(CategoriaRepository.class);
    }

    @Test
    @DisplayName("A categoria mãe deve ser nula caso um id de Categoria não seja passado no request")
    void categoriaMaeDeveSerNulaCasoCategoriaIdNaoSejaPassada(){
        NovaCategoriaRequest categoriaRequest = new NovaCategoriaRequest("Celular", null);
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);

        Assertions.assertNull(categoria.getCategoriaMae());
    }

    @Test
    @DisplayName("A categoria mãe não deve ser nula caso um id de Categoria válido seja passada no request")
    void categoriaMaeNaoDeveSerNulaCasoCategoriaIdValidaSejaPassada(){
        NovaCategoriaRequest categoriaRequest = new NovaCategoriaRequest("Celular", 1L);

        Categoria categoriaMae = new Categoria("Roupa");

        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoriaMae));

        Categoria categoria = categoriaRequest.toModel(categoriaRepository);

        Assertions.assertNotNull(categoria.getCategoriaMae());
        Assertions.assertEquals("Roupa", categoria.getCategoriaMae().getNome());
    }

    @Test
    @DisplayName("A categoria mãe deve ser nula caso um id de categoria inválido seja passado no request")
    void categoriaMaeDeveSerNulaCasoCategoriaIdSejaPassadaMasNaoSejaValida(){
        NovaCategoriaRequest categoriaRequest = new NovaCategoriaRequest("Celular", 10L);

        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);

        Assertions.assertNull(categoria.getCategoriaMae());
    }
}
