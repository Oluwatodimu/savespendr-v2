package com.savespendr.backend.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(predicateSpec -> predicateSpec
						.path("/api/v2/savespendr/users/**")
						.filters(f -> f.rewritePath("/api/v2/savespendr/users/(?<segment>.*)", "/users/${segment}"))
						.uri("lb://user-management-service"))
				.route(predicateSpec -> predicateSpec
						.path("/api/v2/savespendr/merchants/**")
						.filters(f -> f.rewritePath("/api/v2/savespendr/merchants/(?<segment>.*)", "/merchants/${segment}"))
						.uri("lb://merchant-service"))
				.build();
	}
}
