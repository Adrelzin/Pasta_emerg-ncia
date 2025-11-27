package com.loja.loja.controller;

import com.loja.loja.model.Cliente;
import com.loja.loja.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    // READ - Listar todos os clientes
    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    // READ - Buscar cliente por ID
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE - Criar novo cliente
    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    // UPDATE - Atualizar cliente existente
    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return repository.save(cliente);
    }

    // DELETE - Remover cliente
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
