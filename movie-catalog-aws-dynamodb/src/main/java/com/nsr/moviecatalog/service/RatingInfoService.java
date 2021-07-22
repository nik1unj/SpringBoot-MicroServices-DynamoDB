package com.nsr.moviecatalog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nsr.moviecatalog.models.Movie;
import com.nsr.moviecatalog.models.Rating;
import com.nsr.moviecatalog.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingInfoService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;


	@HystrixCommand(fallbackMethod = "getFallBackUserRating")
	public UserRating getUserRating(String userId) {
		/*
		 * return
		 * webClientBuilder.build().get().uri("http://localhost:8083/movieRating/" +
		 * userId) .retrieve() .bodyToMono(UserRating.class).block();
		 */
		 UserRating userRating=restTemplate.getForObject("http://localhost:8083/movieRating/"+userId,UserRating.class);
		// UserRating.class);
		 return userRating;
	}

	
	public UserRating getFallBackUserRating(String userId) {
		List<Map<String, Integer>> userRatingList = new ArrayList<>();
		UserRating userRating = new UserRating();
		Map<String, Integer> map = new HashMap<>();
		map.put("NotFound", 0);
		userRatingList.add(map);
		userRating.setUserRatingList(userRatingList);
		return userRating;
	}
	 }
