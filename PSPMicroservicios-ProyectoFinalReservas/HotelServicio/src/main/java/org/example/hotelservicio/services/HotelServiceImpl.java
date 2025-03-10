package org.example.hotelservicio.services;

import org.example.hotelservicio.models.Hotel;
import org.example.hotelservicio.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> obtenerHotelPorId(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Hotel crearHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> actualizarHotel(Long id, Hotel hotelActualizado) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setNombre(hotelActualizado.getNombre());
                    hotel.setCategoria(hotelActualizado.getCategoria());
                    hotel.setPrecio(hotelActualizado.getPrecio());
                    hotel.setDisponible(hotelActualizado.getDisponible());
                    return hotelRepository.save(hotel);
                });
    }

    @Override
    public boolean eliminarHotel(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void actualizarDisponibilidad(Long id, boolean disponible) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado"));
        hotel.setDisponible(disponible);
        hotelRepository.save(hotel);
    }

    @Override
    public Boolean obtenerDisponibilidad(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado"));
        return hotel.getDisponible();
    }

}