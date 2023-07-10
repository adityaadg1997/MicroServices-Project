package com.aditya.rating.services;

import com.aditya.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //read
    Rating getRating(String ratingId);

    //read all
    List<Rating> getAllRating();

    //read all rating by userId
    List<Rating> getRatingsByUser(String userId);

    //read all rating by hotelId
    List<Rating> getRatingsByHotel(String hotelId);

}
