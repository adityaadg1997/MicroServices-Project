package com.codewithaditya.user.service.UserService.services.impl;

import com.codewithaditya.user.service.UserService.entities.Hotel;
import com.codewithaditya.user.service.UserService.entities.Rating;
import com.codewithaditya.user.service.UserService.entities.User;
import com.codewithaditya.user.service.UserService.exception.ResourceNotFoundException;
import com.codewithaditya.user.service.UserService.external.service.HotelService;
import com.codewithaditya.user.service.UserService.repositories.UserRepository;
import com.codewithaditya.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User createUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);

        return this.userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with UserId : " + userId));

        //here if we'll call below url with above userId then we can fetch all the ratings associated with usedId, like below example url
        //http://localhost:8083/ratings/users/79bb39b7-0c45-43c5-89e4-79f551278c0d

        Rating[] ratingOfUser = this.restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        logger.info("{}", ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        //here we'll call http://localhost:8082/hotels/30edbdc5-1f80-400f-9b5e-8954c3630111,
        // to get details pf hotels associated with the hotelId
        List<Rating> ratingList = ratings.stream().map(rating -> {
                    System.out.println("hotel id from rating " + rating.getHotelId());

                    //call hotel service to fetch hotel details associated with each rating
//                    ResponseEntity<Hotel> forEntity = this.restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//                    Hotel hotel = forEntity.getBody();

                    Hotel hotel = this.hotelService.getHotel(rating.getHotelId());

//                    logger.info("response status code : {}", forEntity.getStatusCode());

                    //set hotel to rating
                    rating.setHotel(hotel);

                    //return the rating
                    return rating;
                }

        ).collect(Collectors.toList());


        user.setRatings(ratingList);


        return user;
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
