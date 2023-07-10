package com.aditya.rating.services.impl;

import com.aditya.rating.entities.Rating;
import com.aditya.rating.repositories.RatingRepository;
import com.aditya.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);

        return this.ratingRepository.save(rating);
    }

    @Override
    public Rating getRating(String ratingId) {
        return null;
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUser(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotel(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
