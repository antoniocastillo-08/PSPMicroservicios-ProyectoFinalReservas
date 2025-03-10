package org.example.reservas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "VUELOSERVICIO")
public interface VuelosCliente {
    @GetMapping("/vuelos/{id}/plazas")
    Integer obtenerPlazasDisponibles(@PathVariable Long id);

    @PutMapping("/vuelos/{id}/plazas")
    void actualizarPlazas(@PathVariable Long id);
}