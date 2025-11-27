package com.loja.loja.controller;

import com.loja.loja.model.Pagamento;
import com.loja.loja.repository.PagamentoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoRepository repository;

    public PagamentoController(PagamentoRepository repository) {
        this.repository = repository;
    }

    // READ - Listar todos os pagamentos
    @GetMapping
    public List<Pagamento> listar() {
        return repository.findAll();
    }

    // READ - Buscar pagamento por ID
    @GetMapping("/{id}")
    public Pagamento buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE - Criar novo pagamento
    @PostMapping
    public Pagamento criar(@RequestBody Pagamento pagamento) {
        return repository.save(pagamento);
    }

    // UPDATE - Atualizar pagamento existente
    @PutMapping("/{id}")
    public Pagamento atualizar(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        pagamento.setId(id);
        return repository.save(pagamento);
    }

    // DELETE - Remover pagamento
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
