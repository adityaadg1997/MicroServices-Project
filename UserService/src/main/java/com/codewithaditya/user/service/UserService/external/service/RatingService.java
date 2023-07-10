package com.codewithaditya.user.service.UserService.external.service;

import com.codewithaditya.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //get rating

    //create rating
    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    //update rating
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    //delete rating
}
