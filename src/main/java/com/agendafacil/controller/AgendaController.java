package com.agendafacil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final List<Map<String, Object>> contatos = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> status() {
        return ResponseEntity.ok(Map.of(
            "status", "online",
            "app", "agendafacil"
        ));
    }

    @GetMapping("/contatos")
    public ResponseEntity<List<Map<String, Object>>> listar() {
        return ResponseEntity.ok(contatos);
    }

    @PostMapping("/contatos")
    public ResponseEntity<Map<String, Object>> criar(@RequestBody Map<String, Object> contato) {
        contato.put("id", contador.incrementAndGet());
        contatos.add(contato);
        return ResponseEntity.status(201).body(contato);
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = contatos.removeIf(c -> id.equals(c.get("id")));
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
