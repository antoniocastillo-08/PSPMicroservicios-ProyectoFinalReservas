package org.example.vuelosservicio.services;

import org.example.vuelosservicio.models.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {
    // Obtener todos los vuelos
    List<Vuelo> obtenerTodosLosVuelos();

    // Obtener un vuelo por ID
    Optional<Vuelo> obtenerVueloPorId(Long id);

    // Crear un nuevo vuelo
    Vuelo crearVuelo(Vuelo vuelo);

    // Actualizar un vuelo existente
    Optional<Vuelo> actualizarVuelo(Long id, Vuelo vueloActualizado);

    Integer obtenerPlazasDisponibles(Long id);

    // Eliminar un vuelo por ID
    boolean eliminarVuelo(Long id);
    void actualizarPlazas(Long id);

}