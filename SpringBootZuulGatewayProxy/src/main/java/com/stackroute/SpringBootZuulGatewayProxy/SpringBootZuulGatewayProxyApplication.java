package com.stackroute.SpringBootZuulGatewayProxy;

import com.stackroute.SpringBootZuulGatewayProxy.filters.ErrorFilter;
import com.stackroute.SpringBootZuulGatewayProxy.filters.PostFilter;
import com.stackroute.SpringBootZuulGatewayProxy.filters.PreFilter;
import com.stackroute.SpringBootZuulGatewayProxy.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringBootZuulGatewayProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootZuulGatewayProxyApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}

