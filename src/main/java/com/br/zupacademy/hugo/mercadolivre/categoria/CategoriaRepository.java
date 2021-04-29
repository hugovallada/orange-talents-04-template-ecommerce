package com.br.zupacademy.hugo.mercadolivre.categoria;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
}
