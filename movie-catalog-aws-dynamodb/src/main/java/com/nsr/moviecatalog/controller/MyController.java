package com.nsr.moviecatalog.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsr.moviecatalog.models.CatalogItem;
import com.nsr.moviecatalog.models.Movie;
import com.nsr.moviecatalog.models.UserRating;
import com.nsr.moviecatalog.service.MovieInfo;
import com.nsr.moviecatalog.service.RatingInfoService;

@RestController
public class MyController {

	@Value("${server.port}")
	String port;

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private RatingInfoService ratingInfoService;

	@RequestMapping("/home/{userId}")
	public List<Stream<CatalogItem>> home(@PathVariable("userId") String userId) {
		UserRating ratings = ratingInfoService.getUserRating(userId);
		System.out.println(port);
		System.out.println(ratings);

		return ratings.getUserRatingList().stream()
				.map(userRatingMap -> userRatingMap.entrySet().stream()
						.map(userRating -> {
									Movie movie = movieInfo.getMovie(userRating.getKey());
									return new CatalogItem(movie.getMovieName(), movie.getMovieDesc(), userRating.getValue());
				})).collect(Collectors.toList());

	}

}
