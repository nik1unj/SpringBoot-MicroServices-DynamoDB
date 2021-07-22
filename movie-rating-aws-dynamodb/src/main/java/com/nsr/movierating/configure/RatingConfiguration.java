package com.nsr.movierating.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class RatingConfiguration {
	@Bean
	public DynamoDBMapper getdynamoDbMapper() {
		return new DynamoDBMapper(getDynamoDBObject());
	}

	public AmazonDynamoDB getDynamoDBObject() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration("your url", "your region"))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials("your key", "your value")))
				.build();

	}
}
