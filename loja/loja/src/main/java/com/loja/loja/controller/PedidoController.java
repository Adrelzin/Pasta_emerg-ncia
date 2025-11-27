package com.loja.loja.controller;

import com.loja.loja.model.Pedido;
import com.loja.loja.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    // READ - Listar todos os pedidos
    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    // READ - Buscar pedido por ID
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE - Criar novo pedido
    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return repository.save(pedido);
    }

    // UPDATE - Atualizar pedido existente
    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return repository.save(pedido);
    }

    // DELETE - Remover pedido
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
