package com.helospark.hibernatetest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Test form to validate.
 * Note: The more fields have validation the higher the likelihood of the concurrency
 * issue to show
 * @author helospark
 */
public class TestForm {

	@NotNull
	@NotEmpty
	@QueryParam("data")
	private String data;

	@NotNull
	@Min(10)
	@QueryParam("data2")
	private Integer data2;

	@NotNull
	@Pattern(regexp = ".*data")
	@QueryParam("data3")
	private String data3;

	@NotNull
	@NotEmpty
	@QueryParam("data4")
	private String data4;

	@NotNull
	@Min(10)
	@QueryParam("data5")
	private Integer data5;

	@NotNull
	@Pattern(regexp = ".*data")
	@QueryParam("data6")
	private String data6;

	@NotNull
	@NotEmpty
	@QueryParam("data7")
	private String data7;

	@NotNull
	@Min(10)
	@QueryParam("data8")
	private Integer data8;

	@NotNull
	@Pattern(regexp = ".*data")
	@QueryParam("data9")
	private String data9;

	@NotNull
	@NotEmpty
	@QueryParam("data10")
	private String data10;

	@NotNull
	@Min(10)
	@QueryParam("data11")
	private Integer data11;

	@NotNull
	@Pattern(regexp = ".*data")
	@QueryParam("data12")
	private String data12;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
