package com.eric.microservice.demo.rating.data.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.eric.microservice.demo.rating.data.service.entity.Rating;

public interface RatingRepository extends CrudRepository<Rating, String>{

}
