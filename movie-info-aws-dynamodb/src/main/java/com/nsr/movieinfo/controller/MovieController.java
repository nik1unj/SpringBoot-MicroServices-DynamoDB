package com.nsr.movieinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.nsr.movieinfo.model.Movie;
import com.nsr.movieinfo.service.MovieInfoService;

@RestController
@RequestMapping("/movieInfo")
public class MovieController {
	
	@Value("${server.port}")
    String port;
	/*
	 * @RequestMapping("/movies/{movieId}") public Movie
	 * getMovie(@PathVariable("movieId") String movieId){ System.out.println(port);
	 * return new Movie(movieId,"Dhoommmmmm"); }
	 */
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	
	@PostMapping("/add")
	public Movie addMovie(@RequestBody Movie movie) {
		return movieInfoService.addMovie(movie);
	}
	
	@RequestMapping("/{movieId}")
	public Movie findMovieById(@PathVariable("movieId") String movieId) {
		return movieInfoService.findMovieById(movieId);
	}
	
	@PutMapping("/update/{movieId}")
	public ResponseEntity<String> updateMovie(@PathVariable("movieId") String movieId, @RequestBody Movie movie) {
		return movieInfoService.updateMovie(movieId, movie);
	}
	
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable("movieId") String movieId){
		return movieInfoService.deleteMovie(movieId);
	}
	
	
	@RequestMapping("/getName/{movieName}")
    public Movie getMovie(@PathVariable("movieName") String movieName) {
	return movieInfoService.findByName(movieName);
	}
	
	
}
