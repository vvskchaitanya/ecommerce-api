package com.vvsk.app.ecommerce.dto.request.usermanagement;

import javax.validation.constraints.NotEmpty;

import com.vvsk.app.ecommerce.dto.request.Request;
import com.vvsk.app.ecommerce.entity.User;

public class AddUserRequest extends Request {

	@NotEmpty(message = "Name is mandatory")
	private String name;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "FirstName is required")
	private String firstName;
	private String lastName;

	public AddUserRequest(String name, String password, String firstName, String lastName) {
		super();
		this.name = name;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User getUser() {
		User user = new User();
		user.setName(name);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		return user;

	}

}
