package com.codewithaditya.user.service.UserService;

import com.codewithaditya.user.service.UserService.entities.Rating;
import com.codewithaditya.user.service.UserService.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(10).feedback("testing feedback").userId("").hotelId("").build();
		Rating createdRating = ratingService.createRating(rating);

		System.out.println("new Rating created");
	}

}
