package com.loja.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.loja.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}
