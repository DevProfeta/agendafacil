package com.agendafacil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final List<String> agendamentos = new ArrayList<>(List.of(
        "Consulta médica - 22/04 às 10h",
        "Reunião de equipe - 23/04 às 14h",
        "Revisão do carro - 24/04 às 09h"
    ));

    @GetMapping
    public ResponseEntity<List<String>> listar() {
        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody String texto) {
        agendamentos.add(texto);
        return ResponseEntity.status(201).body("Agendamento recebido: " + texto);
    }
}
