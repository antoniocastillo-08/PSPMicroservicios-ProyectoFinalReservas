package org.example.hotelservicio.repositories;

import org.example.hotelservicio.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}