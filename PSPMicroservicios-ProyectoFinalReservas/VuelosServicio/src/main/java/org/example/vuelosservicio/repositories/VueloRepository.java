package org.example.vuelosservicio.repositories;

import org.example.vuelosservicio.models.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}