package com.loja.loja.controller;

import com.loja.loja.model.Produto;
import com.loja.loja.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    // READ - Listar todos os produtos
    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    // READ - Buscar produto por ID
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // CREATE - Criar novo produto
    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    // UPDATE - Atualizar produto existente
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return repository.save(produto);
    }

    // DELETE - Remover produto
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
