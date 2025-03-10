package org.example.hotelservicio.controllers;

import org.example.hotelservicio.models.Hotel;
import org.example.hotelservicio.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hoteles")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // GET: Obtener todos los hoteles
    @GetMapping
    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelService.obtenerTodosLosHoteles();
    }
    @GetMapping("/{id}/disponibilidad")
    public Boolean obtenerDisponibilidad(@PathVariable Long id) {
        return hotelService.obtenerDisponibilidad(id);
    }
    // GET: Obtener un hotel por ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerHotelPorId(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelService.obtenerHotelPorId(id);
        return hotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo hotel
    @PostMapping
    public ResponseEntity<Hotel> crearHotel(@RequestBody Hotel hotel) {
        Hotel nuevoHotel = hotelService.crearHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHotel);
    }

    // PUT: Actualizar un hotel existente
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> actualizarHotel(@PathVariable Long id, @RequestBody Hotel hotelActualizado) {
        Optional<Hotel> hotel = hotelService.actualizarHotel(id, hotelActualizado);
        return hotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/disponibilidad")
    public ResponseEntity<String> actualizarDisponibilidad(@PathVariable Long id, @RequestParam boolean disponible) {
        hotelService.actualizarDisponibilidad(id, disponible);
        return ResponseEntity.ok("Disponibilidad actualizada");
    }

    // DELETE: Eliminar un hotel por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long id) {
        if (hotelService.eliminarHotel(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}