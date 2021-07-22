package com.nsr.movierating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nsr.movierating.model.Rating;
import com.nsr.movierating.model.UserRating;

@Service
public class MovieRatingService {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	
	
	public Rating addRating(Rating rating) {
		dynamoDBMapper.save(rating);
		return rating;
	}

	public UserRating findByUserId(String userId) {
		UserRating userR = new UserRating();
		Rating userRating= dynamoDBMapper.load(Rating.class,userId);
		if(userRating != null) {
			userR.setUserRatingList(userRating.getMovieRatingList());
			return userR;
		}
		return new UserRating() ;
	}
	
	
}
