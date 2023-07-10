package com.codewithaditya.HotelService.services.impl;

import com.codewithaditya.HotelService.entities.Hotel;
import com.codewithaditya.HotelService.exception.ResourceNotFoundException;
import com.codewithaditya.HotelService.repositories.HotelRepository;
import com.codewithaditya.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);

        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not Found with hotelId " + hotelId));
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }
}
