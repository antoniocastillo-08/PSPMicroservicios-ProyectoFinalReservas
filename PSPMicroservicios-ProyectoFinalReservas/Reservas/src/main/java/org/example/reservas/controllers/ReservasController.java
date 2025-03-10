package org.example.reservas.controllers;

import org.example.reservas.client.HotelCliente;
import org.example.reservas.models.Reserva;
import org.example.reservas.services.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;
    private HotelCliente hotelCliente;

    // GET: Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservasService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reservas);
    }

    // GET: Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Long id) {
        Reserva reserva = reservasService.obtenerReservaPorId(id);
        return ResponseEntity.ok(reserva);
    }

    // POST: Crear una nueva reserva
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        try {
            reservasService.crearReserva(reserva);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

    // PUT: Actualizar una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        reservasService.actualizarReserva(id, reserva);
        return ResponseEntity.ok("Reserva actualizada con éxito");
    }

    // DELETE: Eliminar una reserva por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) {
        reservasService.eliminarReserva(id);

        return ResponseEntity.ok("Reserva eliminada con éxito");
    }
}