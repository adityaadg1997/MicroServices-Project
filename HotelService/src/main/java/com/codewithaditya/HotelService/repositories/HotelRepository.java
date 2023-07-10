package com.codewithaditya.HotelService.repositories;

import com.codewithaditya.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
