package com.loja.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.loja.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
