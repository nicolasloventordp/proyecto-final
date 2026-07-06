package com.example.proyecto_final.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto_final.exception.MarcaInexistente;
import com.example.proyecto_final.model.Marca;
import com.example.proyecto_final.repository.MarcaRepository;

@Service
public class MarcaService {
    // atributos
    private MarcaRepository repository;

    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    // lista todos los marcas
    public List<Marca> listarTodos() {
        return repository.findAll();
    }

    // Obtiene un marca por id si no existe lanza excepción
    public Marca obtenerPorId(int id) throws MarcaInexistente {
        return repository.findById(id)
                .orElseThrow( () -> new MarcaInexistente("No hay marca con id " + id));
        
    }

    // guarda una marca
    public Marca guardar(Marca m) {
        repository.save(m);
        return m;
    }

    //Elimina todos los registros de la db
    public void deleteAll() {
        repository.deleteAll();
    }

    // actualizar una marca
    public Marca actualizar(int id, Marca datos) throws MarcaInexistente {
        Marca m = obtenerPorId(id);
        m.setNombre(datos.getNombre());
        repository.save(m);
        return m;
    }

    // eliminar marca
    public void eliminar(int id) throws MarcaInexistente {
        Marca m = obtenerPorId(id);
        repository.delete(m);
    }
}
