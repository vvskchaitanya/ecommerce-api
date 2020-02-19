package com.vvsk.app.ecommerce.dto.response;

import java.util.Map;

public class Response {

	private String status;
	private long size;
	private Map<String, String> errors;

	public Response(String status) {
		super();
		this.status = status;
	}

	public Response(Map<String, String> errors) {
		super();
		this.status = "Validation Error";
		this.errors = errors;
	}

}
