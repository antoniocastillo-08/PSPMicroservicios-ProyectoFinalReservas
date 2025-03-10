package org.example.reservas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "HOTELSERVICIO")
public interface HotelCliente {
    @GetMapping("/hoteles/{id}/disponibilidad")
    Boolean obtenerDisponibilidad(@PathVariable Long id);

    @PutMapping("/hoteles/{id}/disponibilidad")
    void actualizarDisponibilidad(@PathVariable Long id, @RequestParam boolean disponible);
}