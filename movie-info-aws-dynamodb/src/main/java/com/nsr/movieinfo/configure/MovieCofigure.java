package com.nsr.movieinfo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class MovieCofigure {
	@Bean
	public DynamoDBMapper getdynamoDbMapper() {
		return new DynamoDBMapper(getDynamoDBObject());
	}

	public AmazonDynamoDB getDynamoDBObject() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-2.amazonaws.com", "us-east-2"))
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials("AKIAU7KBK6PLFGTNSLDG", "FqTexe+wYTwd/Ivrv1XiVIBq4034feHQv96WQakM")))
				.build();

	}
}
