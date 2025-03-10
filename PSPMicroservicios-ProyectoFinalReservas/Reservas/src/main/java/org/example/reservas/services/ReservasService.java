package org.example.reservas.services;

import org.example.reservas.models.Reserva;
import java.util.List;

public interface ReservasService {
    List<Reserva> obtenerTodasLasReservas();
    Reserva obtenerReservaPorId(Long id);
    void crearReserva(Reserva reserva);
    void actualizarReserva(Long id, Reserva reserva);
    void eliminarReserva(Long id);
}