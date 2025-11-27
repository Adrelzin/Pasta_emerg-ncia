package com.loja.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.loja.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
