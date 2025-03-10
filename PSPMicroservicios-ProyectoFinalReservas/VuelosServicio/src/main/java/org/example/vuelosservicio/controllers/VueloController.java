package org.example.vuelosservicio.controllers;

import org.example.vuelosservicio.models.Vuelo;
import org.example.vuelosservicio.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
    @Autowired
    private VueloService vueloService;

    // GET: Obtener todos los vuelos
    @GetMapping
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloService.obtenerTodosLosVuelos();
    }

    // GET: Obtener un vuelo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> obtenerVueloPorId(@PathVariable Long id) {
        Optional<Vuelo> vuelo = vueloService.obtenerVueloPorId(id);
        return vuelo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/plazas")
    public ResponseEntity<Integer> obtenerPlazasDisponibles(@PathVariable Long id) {
        try {
            Integer plazasDisponibles = vueloService.obtenerPlazasDisponibles(id);
            return ResponseEntity.ok(plazasDisponibles);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // POST: Crear un nuevo vuelo
    @PostMapping
    public ResponseEntity<Vuelo> crearVuelo(@RequestBody Vuelo vuelo) {
        Vuelo nuevoVuelo = vueloService.crearVuelo(vuelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVuelo);
    }

    // PUT: Actualizar un vuelo existente
    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> actualizarVuelo(@PathVariable Long id, @RequestBody Vuelo vueloActualizado) {
        Optional<Vuelo> vuelo = vueloService.actualizarVuelo(id, vueloActualizado);
        return vuelo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un vuelo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVuelo(@PathVariable Long id) {
        if (vueloService.eliminarVuelo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Actualizar las plazas disponibles de un vuelo (restar 1 plaza)
    @PutMapping("/{id}/plazas")
    public ResponseEntity<String> actualizarPlazas(@PathVariable Long id) {
        vueloService.actualizarPlazas(id);
        return ResponseEntity.ok("Plaza restada correctamente");
    }
}