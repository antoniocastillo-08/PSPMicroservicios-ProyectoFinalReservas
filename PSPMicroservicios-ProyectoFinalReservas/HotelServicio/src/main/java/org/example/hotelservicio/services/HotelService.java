package org.example.hotelservicio.services;

import org.example.hotelservicio.models.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    // Obtener todos los hoteles
    List<Hotel> obtenerTodosLosHoteles();

    // Obtener un hotel por ID
    Optional<Hotel> obtenerHotelPorId(Long id);

    // Crear un nuevo hotel
    Hotel crearHotel(Hotel hotel);

    // Actualizar un hotel existente
    Optional<Hotel> actualizarHotel(Long id, Hotel hotelActualizado);

    // Eliminar un hotel por ID
    boolean eliminarHotel(Long id);

    void actualizarDisponibilidad(Long id, boolean disponible);

    Boolean obtenerDisponibilidad(Long id);
}