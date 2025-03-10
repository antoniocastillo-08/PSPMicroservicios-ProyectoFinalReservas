package org.example.reservas.services;

import org.example.reservas.client.HotelCliente;
import org.example.reservas.client.VuelosCliente;
import org.example.reservas.models.Reserva;
import org.example.reservas.repositories.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasServiceImpl implements ReservasService {

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private VuelosCliente vuelosClient;

    @Autowired
    private HotelCliente hotelClient;

    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        return reservasRepository.findAll();
    }

    @Override
    public Reserva obtenerReservaPorId(Long id) {
        Optional<Reserva> reserva = reservasRepository.findById(id);
        return reserva.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    @Transactional
    public void crearReserva(Reserva reserva) {
        // Verificar que el ID del hotel no sea nulo
        if (reserva.getIdHotel() == null) {
            throw new IllegalArgumentException("El ID del hotel no puede ser nulo");
        }

        // Verificar que el ID del vuelo no sea nulo
        if (reserva.getIdVuelo() == null) {
            throw new IllegalArgumentException("El ID del vuelo no puede ser nulo");
        }

        // Obtener la disponibilidad del hotel
        Boolean disponible = hotelClient.obtenerDisponibilidad(reserva.getIdHotel());
        if (disponible == null || !disponible) {
            throw new IllegalStateException("El hotel no está disponible");
        }

        // Obtener las plazas disponibles del vuelo
        Integer plazasDisponibles = vuelosClient.obtenerPlazasDisponibles(reserva.getIdVuelo());
        if (plazasDisponibles == null || plazasDisponibles <= 0) {
            throw new IllegalStateException("No hay plazas disponibles en el vuelo");
        }

        // Actualizar la disponibilidad del hotel
        hotelClient.actualizarDisponibilidad(reserva.getIdHotel(), false);

        // Actualizar las plazas del vuelo
        vuelosClient.actualizarPlazas(reserva.getIdVuelo());

        // Guardar la reserva en la base de datos
        reservasRepository.save(reserva);
    }

    @Override
    @Transactional
    public void actualizarReserva(Long id, Reserva reserva) {
        Reserva reservaExistente = obtenerReservaPorId(id);

        // Actualizar los campos de la reserva existente
        reservaExistente.setNombreUsuario(reserva.getNombreUsuario());
        reservaExistente.setDniUsuario(reserva.getDniUsuario());
        reservaExistente.setIdHotel(reserva.getIdHotel());
        reservaExistente.setIdVuelo(reserva.getIdVuelo());

        // Guardar la reserva actualizada
        reservasRepository.save(reservaExistente);
    }

    @Override
    @Transactional
    public void eliminarReserva(Long id) {
        Reserva reserva = obtenerReservaPorId(id);

        // Actualizar disponibilidad en el servicio de hoteles
        hotelClient.actualizarDisponibilidad(reserva.getIdHotel(), true);

        // Eliminar la reserva de la base de datos
        reservasRepository.deleteById(id);
    }
}