package org.example.vuelosservicio.services;

import org.example.vuelosservicio.models.Vuelo;
import org.example.vuelosservicio.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public Optional<Vuelo> obtenerVueloPorId(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Optional<Vuelo> actualizarVuelo(Long id, Vuelo vueloActualizado) {
        return vueloRepository.findById(id)
                .map(vuelo -> {
                    vuelo.setCompania(vueloActualizado.getCompania());
                    vuelo.setFecha(vueloActualizado.getFecha());
                    vuelo.setPrecio(vueloActualizado.getPrecio());
                    vuelo.setPlazasDisponibles(vueloActualizado.getPlazasDisponibles());
                    return vueloRepository.save(vuelo);
                });
    }

    @Override
    @Transactional
    public void actualizarPlazas(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        if (vuelo.getPlazasDisponibles() > 0) {
            vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - 1); // Restar 1 plaza
            vueloRepository.save(vuelo);
        } else {
            throw new RuntimeException("No hay plazas disponibles en el vuelo");
        }
    }
    @Override
    public Integer obtenerPlazasDisponibles(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        return vuelo.getPlazasDisponibles();
    }

    @Override
    public boolean eliminarVuelo(Long id) {
        if (vueloRepository.existsById(id)) {
            vueloRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}