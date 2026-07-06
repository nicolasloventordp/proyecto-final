package com.example.proyecto_final.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto_final.exception.AutoInexistente;
import com.example.proyecto_final.model.Auto;
import com.example.proyecto_final.service.AutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autos")
@CrossOrigin
public class AutoController {
    // Inyección por constructor
    private AutoService service;

    public AutoController(AutoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Auto>> listarAutos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int id) throws AutoInexistente {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Auto> crearProducto(@Valid @RequestBody Auto nuevoAuto) throws AutoInexistente {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(nuevoAuto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auto> actualizar(
            @PathVariable Integer id, @Valid @RequestBody Auto datos) throws AutoInexistente {
        return ResponseEntity.ok(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws AutoInexistente {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
