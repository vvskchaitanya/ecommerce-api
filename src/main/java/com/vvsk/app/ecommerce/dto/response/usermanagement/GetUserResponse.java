package com.vvsk.app.ecommerce.dto.response.usermanagement;

import java.util.List;

import com.vvsk.app.ecommerce.dto.response.Response;
import com.vvsk.app.ecommerce.entity.User;

public class GetUserResponse extends Response {

	String name;
	List<String> roles;
	String firstName;
	String lastName;
	boolean active;

	public GetUserResponse(User user) {
		super("Success");
		this.name = user.getName();
		this.roles = user.getRole();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.active = user.isActive();
	}

}
