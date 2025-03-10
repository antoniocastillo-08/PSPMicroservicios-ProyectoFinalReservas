package org.example.vuelosservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "org.example.vuelosservicio")
public class VuelosServicioApplication {
    public static void main(String[] args) {
        SpringApplication.run(VuelosServicioApplication.class, args);
    }
}