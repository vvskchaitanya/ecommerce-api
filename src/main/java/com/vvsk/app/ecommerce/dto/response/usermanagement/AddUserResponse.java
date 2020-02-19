package com.vvsk.app.ecommerce.dto.response.usermanagement;

import java.util.List;

import com.vvsk.app.ecommerce.dto.response.Response;
import com.vvsk.app.ecommerce.entity.User;

public class AddUserResponse extends Response {

	private String name;
	private List<String> roles;
	private String firstName;
	private String lastName;
	private boolean active;

	public AddUserResponse(User user) {
		super("Success");
		this.name = user.getName();
		this.roles = user.getRole();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.active = user.isActive();
	}

}
