package com.eric.microservice.demo.rating.data.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric.microservice.demo.rating.data.service.entity.Rating;
import com.eric.microservice.demo.rating.data.service.entity.UserRating;
import com.eric.microservice.demo.rating.data.service.repository.RatingRepository;

@RestController
@RequestMapping("/rating")
public class RatingDataController {
	@Autowired
	private RatingRepository ratingRepository;

	@GetMapping("/movies/{movieId}")
	public List<Rating> getMovieRating(@PathVariable("movieId") String movieId) {
		List<Rating> movieRating = new ArrayList<>();
		ratingRepository.findAll().forEach(rating -> {
			if (rating.getMovieId().equalsIgnoreCase(movieId)) {
				movieRating.add(new Rating(movieId, rating.getRating()));
			}
		});
		return movieRating;
	}

	@GetMapping("/user/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.initData(userId);
		return userRating;

	}

	@PostMapping("/{rating}/{movieId}")
	public void rateMovie(@PathVariable int rating, @PathVariable String movieId) {
		ratingRepository.save(new Rating(movieId, rating));
	}
}
