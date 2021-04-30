package com.br.zupacademy.hugo.mercadolivre.produto;

import com.br.zupacademy.hugo.mercadolivre.caracteristica.CaracteristicaProduto;
import com.br.zupacademy.hugo.mercadolivre.caracteristica.NovaCaracteristicaRequest;
import com.br.zupacademy.hugo.mercadolivre.categoria.Categoria;
import com.br.zupacademy.hugo.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String nome;

    private @Positive @NotNull BigDecimal valor;

    private @PositiveOrZero @NotNull Integer quantidade;


    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @Size(min = 3)
    @Valid
    private Set<CaracteristicaProduto> caracteristicaProdutos = new HashSet<>();

    private @NotBlank @Size(max = 1000) String descricao;

    @NotNull
    @ManyToOne
    @Valid
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @Valid
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime instanteCadastro;

    /**
     * @deprecated Cosntrutor de uso exclusivo do JPA
     */
    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome,@Positive @NotNull BigDecimal valor,@PositiveOrZero @NotNull Integer quantidade,
                   @Size(min = 3) @NotNull @Valid  Set<NovaCaracteristicaRequest> caracteristicaProdutos,
                   @NotBlank @Size(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario usuario) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicaProdutos.addAll(caracteristicaProdutos.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

        Assert.isTrue(caracteristicaProdutos.size() >= 3, "O produto deve ter pelo menos 3 características");
    }

    public Set<CaracteristicaProduto> getCaracteristicaProdutos() {
        return caracteristicaProdutos;
    }
}
