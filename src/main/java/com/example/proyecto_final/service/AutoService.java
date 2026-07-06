package com.example.proyecto_final.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto_final.exception.AutoInexistente;
import com.example.proyecto_final.model.Auto;
import com.example.proyecto_final.model.Marca;
import com.example.proyecto_final.repository.AutoRepository;
import com.example.proyecto_final.repository.MarcaRepository;

@Service
public class AutoService {
    // atributos
    private AutoRepository repository;
    private MarcaRepository marcaRepository;

    public AutoService(AutoRepository repository, MarcaRepository marcaRepository) {
        this.repository = repository;
        this.marcaRepository = marcaRepository;
    }

    // lista todos los autos
    public List<Auto> listarTodos() {
        return repository.findAll();
    }

    // Obtiene un auto por id si no existe lanza excepción
    public Auto obtenerPorId(int id) throws AutoInexistente {
        return repository.findById(id)
                .orElseThrow( () -> new AutoInexistente("No hay auto con id " + id));
        
    }

    // guarda un auto
    public Auto guardar(Auto a) {
        //primero verifica que la marca exista
        if(a.getMarca().getId() != null) {
            Marca marca = marcaRepository.findById(a.getMarca().getId())
                .orElseThrow( () -> new RuntimeException("La marca no existe."));
            //Si existe asignamos al auto la marca real
            a.setMarca(marca);
        }
        return repository.save(a);
    }

    //Elimina todos los registros de la db
    public void deleteAll() {
        repository.deleteAll();
    }

    // actualizar un auto
    public Auto actualizar(int id, Auto datos) throws AutoInexistente {
        Auto a = obtenerPorId(id);
        a.setModelo(datos.getModelo());
        a.setMarca(datos.getMarca());
        a.setPrecio(datos.getPrecio());
        a.setStock(datos.getStock());
        repository.save(a);
        return a;
    }

    // eliminar auto
    public void eliminar(int id) throws AutoInexistente {
        Auto a = obtenerPorId(id);
        repository.delete(a);
    }

}
