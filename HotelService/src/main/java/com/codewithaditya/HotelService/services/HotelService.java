package com.codewithaditya.HotelService.services;

import com.codewithaditya.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel createHotel(Hotel hotel);

    //read
    Hotel getHotel(String hotelId);

    //read all
   List<Hotel> getAllHotel();

}
