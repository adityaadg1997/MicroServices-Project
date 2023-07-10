package com.aditya.rating.controllers;

import com.aditya.rating.entities.Rating;
import com.aditya.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating createdRating = this.ratingService.createRating(rating);

        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }

    //get all rating
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratingList = this.ratingService.getAllRating();

        return new ResponseEntity<>(ratingList, HttpStatus.OK);
    }

    //get rating by user
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable("userId") String userId){
        List<Rating> ratingsByUser = this.ratingService.getRatingsByUser(userId);

        return new ResponseEntity<>(ratingsByUser, HttpStatus.OK);
    }

    //get rating by hotel
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotel(@PathVariable("hotelId") String hotelId){
        List<Rating> ratingsByHotel = this.ratingService.getRatingsByHotel(hotelId);

        return new ResponseEntity<>(ratingsByHotel, HttpStatus.OK);
    }
}
