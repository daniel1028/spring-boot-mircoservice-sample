package com.eric.microservice.demo.movie.info.service.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eric.microservice.demo.movie.info.service.model.Movie;
import com.eric.microservice.demo.movie.info.service.model.Rating;
import com.eric.microservice.demo.movie.info.service.repository.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MoviewInfoServiceController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/{movieId}")
	public List<Movie> getMovieInfo(@PathVariable String movieId) {
		List<Movie> movies = new ArrayList<>();
		Rating[] ratings = restTemplate.getForObject("http://rating.data.service/rating/movies/" + movieId+"",
				Rating[].class);
		
		for(Rating rating : ratings) {
			movies.add(new Movie(rating.getMovieId(), "Movie name here", "description here"));
		}
	
		System.out.println(movies);
		return movies;

	}

	@PostMapping(path = "/add")
	public @Valid ResponseEntity<Movie> addEmployee(@Valid @RequestBody Movie movie) {
		return ResponseEntity.ok(movieRepository.save(movie));
	}
}
