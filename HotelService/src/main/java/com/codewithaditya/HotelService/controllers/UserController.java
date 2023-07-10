package com.codewithaditya.HotelService.controllers;

import com.codewithaditya.HotelService.entities.Hotel;
import com.codewithaditya.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class UserController {
    
    @Autowired
    private HotelService hotelService;

    //create
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createdHotel = this.hotelService.createHotel(hotel);

        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }
    
    //read
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") String hotelId){
        Hotel hotel = this.hotelService.getHotel(hotelId);

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
    
    //read all
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotelList = this.hotelService.getAllHotel();

        return new ResponseEntity<>(hotelList, HttpStatus.OK);
    }

}
