package com.codewithaditya.user.service.UserService.controller;

import com.codewithaditya.user.service.UserService.entities.User;
import com.codewithaditya.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = this.userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    int retryCount = 1;

    //read
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        logger.info("retryCount: " + retryCount);
        retryCount++;

        User user = this.userService.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //fall back method for rating or hotel service breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception){
        logger.info("fall back method executed because of some services gets interrupted", exception.getMessage());

        User user = User.builder().userId("12345").email("dummy@gmail.com").about("dummy about").name("dummy").build();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    //read all
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userslist = this.userService.getAllUser();

        return new ResponseEntity<>(userslist, HttpStatus.OK);
    }
}
