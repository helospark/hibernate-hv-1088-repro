package com.helospark.hibernatetest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages("com.helospark.hibernatetest");
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true); // 404 -> 400 on validation error
	}

}