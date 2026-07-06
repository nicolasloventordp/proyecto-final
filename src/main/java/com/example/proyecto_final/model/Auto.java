package com.example.proyecto_final.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El modelo no puede estar vacío")
    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Positive(message = "El precio debe ser mayor que cero")
    @Column(name = "precio", nullable = false)
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    public Auto(String modelo,Marca marca, double precio, Integer stock){
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }
}
