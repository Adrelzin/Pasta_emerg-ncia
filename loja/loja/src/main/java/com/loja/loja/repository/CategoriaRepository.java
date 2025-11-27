package com.loja.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.loja.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
