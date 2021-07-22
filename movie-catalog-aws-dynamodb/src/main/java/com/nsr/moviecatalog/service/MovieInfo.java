package com.nsr.moviecatalog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nsr.moviecatalog.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private WebClient.Builder webClientBuilder;

    
    @HystrixCommand(fallbackMethod = "getFallBackForMovieInfo")
    public Movie getMovie(String movieId) {
		/*
		 * return
		 * webClientBuilder.build().get().uri("http://movie-info-service/movieInfo/"+
		 * movieId).retrieve().bodyToMono(Movie.class).block();
		 */
        	return	restTemplate.getForObject("http://localhost:8082/movieInfo/"+movieId,Movie.class);
    }

	
	  public Movie getFallBackForMovieInfo(String movieId){ return new
	  Movie("0","Movie not found","Desc Not Found"); }
	 
}
