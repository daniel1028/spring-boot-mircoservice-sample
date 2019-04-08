package com.eric.microservice.demo.movie.info.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.eric.microservice.demo.movie.info.service.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, String> {

}
