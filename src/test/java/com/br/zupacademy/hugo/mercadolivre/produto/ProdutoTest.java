package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.caracteristica.NovaCaracteristicaRequest;
import com.br.zupacademy.hugo.mercadolivre.categoria.Categoria;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

public class ProdutoTest {

    private Usuario usuario;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria= new Categoria("categoria");
        usuario = new Usuario("email@email.com","123456", new BCryptPasswordEncoder());

    }

    @DisplayName("Um produto precisa ter no mínimo 3 características sem duplicidade de nome")
    @ParameterizedTest
    @MethodSource("geradorDeColecaoDeCaracteristicasComTresOuMaisCaracteristicas")
    void produtoDeveTerNoMinimo3Caracteristicas(Set<NovaCaracteristicaRequest> caracteristicas){
        Produto produto = new Produto("nome", BigDecimal.TEN, 10, caracteristicas, "descricao", categoria, usuario);

        Assertions.assertNotNull(produto);
        Assertions.assertTrue(produto.getCaracteristicaProdutos().size() >= 3);

    }


    @DisplayName("Um produto não deve ser inicializado com menos de 3 características")
    @ParameterizedTest
    @MethodSource(("geradorDeColecaoDeCaracteristicasComDuasOuMenosCaracteristicas"))
    void umaIllegalArgumentExceptionDeveSerLancadaCasoOProdutoTenhaMenosDe3Caracteristicas(Set<NovaCaracteristicaRequest> caracteristicas){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Produto produto = new Produto("nome", BigDecimal.TEN, 10, caracteristicas, "descricao", categoria, usuario);
        });
    }

    /**
     *
     * @return retorna uma coleção de dados a ser usada para testes
     */
    static Stream<Arguments> geradorDeColecaoDeCaracteristicasComTresOuMaisCaracteristicas(){
        return Stream.of(
                Arguments.of(
                        Set.of(new NovaCaracteristicaRequest("key","value"),
                                new NovaCaracteristicaRequest("key1","value1"),
                                new NovaCaracteristicaRequest("key2","value2"))),
                Arguments.of(
                        Set.of(new NovaCaracteristicaRequest("key","value"),
                                new NovaCaracteristicaRequest("key1","valu1e"),
                                new NovaCaracteristicaRequest("key2","value2"),
                                new NovaCaracteristicaRequest("key3","value3")))
        );
    }

    static Stream<Arguments> geradorDeColecaoDeCaracteristicasComDuasOuMenosCaracteristicas(){
        return Stream.of(
                Arguments.of(Set.of(new NovaCaracteristicaRequest("key","value")),
                Arguments.of(
                        Set.of(new NovaCaracteristicaRequest("key","value"),
                                new NovaCaracteristicaRequest("key1","valu1e")))));
    }
}
