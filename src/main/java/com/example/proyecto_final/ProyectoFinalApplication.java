package com.example.proyecto_final;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.proyecto_final.model.Auto;
import com.example.proyecto_final.model.Marca;
import com.example.proyecto_final.service.AutoService;
import com.example.proyecto_final.service.MarcaService;

@SpringBootApplication
public class ProyectoFinalApplication {

	private final MarcaService marcaService;

    ProyectoFinalApplication(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Bean
	public CommandLineRunner cargarAutos(AutoService autoService, MarcaService marcaService) {
		return args -> {
			Marca toyota = new Marca("Toyota");
			Marca peugeot = new Marca("Peugeot");
			Marca citroen = new Marca("Citroen");

			if(marcaService.listarTodos().isEmpty()) {
				marcaService.guardar(toyota);
				marcaService.guardar(peugeot);
				marcaService.guardar(citroen);
			}
			
			if(autoService.listarTodos().isEmpty()) {
				autoService.guardar(new Auto("Xilux",toyota,45000000, 2));
				autoService.guardar(new Auto("207",peugeot,6000000, 4));
				autoService.guardar(new Auto("408",peugeot,8800000, 6));
				autoService.guardar(new Auto("c3",citroen,11500000, 2));
				//autoService.guardar(new Auto("Corolla",2023,4.000.666, 1));
			}
		};
	}

}
