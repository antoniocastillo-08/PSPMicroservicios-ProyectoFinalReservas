package org.example.hotelservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "org.example.hotelservicio.models") // Escanea las entidades
@EnableJpaRepositories(basePackages = "org.example.hotelservicio") // Escanea los repositorios
public class HotelServicioApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelServicioApplication.class, args);
    }
}