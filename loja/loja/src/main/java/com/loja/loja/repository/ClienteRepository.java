package com.loja.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.loja.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
