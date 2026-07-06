package com.example.proyecto_final.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_final.exception.MarcaInexistente;
import com.example.proyecto_final.model.Marca;
import com.example.proyecto_final.service.MarcaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
    // Inyección por constructor
    private MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Marca>> listarMarcas() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMarcaPorId(@PathVariable int id) throws MarcaInexistente {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Marca> crearMarca(@Valid @RequestBody Marca nuevaMarca) throws MarcaInexistente {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevaMarca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizar(
            @PathVariable Integer id, @Valid @RequestBody Marca datos) throws MarcaInexistente {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws MarcaInexistente {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
