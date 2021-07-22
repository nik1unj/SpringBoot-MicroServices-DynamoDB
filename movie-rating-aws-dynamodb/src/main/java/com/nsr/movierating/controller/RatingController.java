package com.nsr.movierating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsr.movierating.model.Rating;
import com.nsr.movierating.model.UserRating;
import com.nsr.movierating.service.MovieRatingService;

@RestController
@RequestMapping("/movieRating")
public class RatingController {

	/*
	 * @RequestMapping("/rating/{movieId}") public Rating
	 * getMovieRating(@PathVariable("movieId") String movieId){ return new
	 * Rating(movieId,4); }
	 * 
	 * 
	 * @RequestMapping("/users/{userId}") public UserRating
	 * getUserRating(@PathVariable("userId") String userId){ List<Rating> ratings =
	 * Arrays.asList( new Rating("1234",4), new Rating("1235",6) );
	 * 
	 * UserRating userRating = new UserRating(); userRating.setUserRating(ratings);
	 * return userRating; }
	 */
	@Autowired
	private MovieRatingService movieRatingService;
	
	@PostMapping("/add")
	public Rating addRating(@RequestBody Rating rating) {
		return movieRatingService.addRating(rating);
	}
	
	@RequestMapping("/{userId}")
	public UserRating findByUserId(@PathVariable("userId") String userId) {
		return movieRatingService.findByUserId(userId);
	}
	
}
