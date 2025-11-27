package com.loja.loja.controller;

import com.loja.loja.model.Cliente;
import com.loja.loja.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Clientes")
public class ClienteViewController {

    private final ClienteRepository repository;

    public ClienteViewController(ClienteRepository repository) {
        this.repository = repository;
    }

    // Listar clientes (GET)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", repository.findAll());
        return "Clientes";
    }

    // Exibir formulário de cadastro (GET)
    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "ClienteForm";
    }

    // Salvar cliente (POST)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {
        repository.save(cliente);
        return "redirect:/Clientes";  // ← MUDEI AQUI
    }

    // Exibir formulário de edição (GET)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = repository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "ClienteForm";
    }

    // Deletar cliente (GET)
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/Clientes";  // ← MUDEI AQUI
    }
}