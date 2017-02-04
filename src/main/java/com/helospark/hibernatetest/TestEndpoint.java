package com.helospark.hibernatetest;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class TestEndpoint {

	@GET
	public String test(@Valid @BeanParam TestForm testForm) {
		return "OK";
	}
}
