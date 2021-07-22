package com.nsr.movieinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.nsr.movieinfo.model.Movie;

@Service
public class MovieInfoService {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public Movie addMovie(Movie movie) {
		dynamoDBMapper.save(movie);
		return movie;
	}
	
	public Movie findMovieById(String movieId) {
		return dynamoDBMapper.load(Movie.class, movieId);
	}
	
	public ResponseEntity<String> updateMovie(String movieId,Movie movie) {
		Movie movieObject=dynamoDBMapper.load(Movie.class,movieId);
		if(movieObject != null) {
			movieObject.setMovieName(movie.getMovieName());
			movieObject.setMovieDesc(movie.getMovieDesc());
			dynamoDBMapper.save(movieObject);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Movie not found",HttpStatus.NOT_FOUND);
		
	}

	public ResponseEntity<String> deleteMovie(String movieId){
		Movie movieObject=dynamoDBMapper.load(Movie.class,movieId);
		if(movieObject != null) {
			dynamoDBMapper.delete(movieObject);
			return new ResponseEntity<String>("Movie Delete Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie not found",HttpStatus.NOT_FOUND);
		
	}
	
	public Movie findByName(String movieName) {
	
		    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		    scanExpression.addFilterCondition("movieName", new Condition()                                           
		       .withComparisonOperator(ComparisonOperator.EQ)                                                
		       .withAttributeValueList(new AttributeValue().withS(movieName)));
		    PaginatedScanList<Movie> movie= dynamoDBMapper.scan(Movie.class, scanExpression);
		    return movie.get(0);
		   
		
	}
	
}
