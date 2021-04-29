package com.br.zupacademy.hugo.mercadolivre.categoria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    void categoriaMaeDeveSerNulaCasoCategoriaIdNaoSejaPassada(){
        NovaCategoriaRequest categoriaRequest = new NovaCategoriaRequest("Celular", null);
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);

        Assertions.assertNull(categoria.getCategoriaMae());
    }

    @Test
    void categoriaMaeNaoDeveSerNulaCasoCategoriaIdSejaPassada(){
        NovaCategoriaRequest categoriaRequest = new NovaCategoriaRequest("Celular", 1L);

        Categoria categoriaMae = new Categoria("Roupa");

        Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoriaMae));

        Categoria categoria = categoriaRequest.toModel(categoriaRepository);

        Assertions.assertNotNull(categoria.getCategoriaMae());
        Assertions.assertEquals("Roupa", categoria.getCategoriaMae().getNome());
    }
}
