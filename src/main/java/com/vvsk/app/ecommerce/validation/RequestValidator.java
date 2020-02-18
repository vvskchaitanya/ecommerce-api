package com.vvsk.app.ecommerce.validation;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vvsk.app.ecommerce.dto.Request;
import com.vvsk.app.ecommerce.entity.User;

@Service
public class RequestValidator {

	public void validateAddUser(Request request) throws ValidationException {
		User user = (User) request;
		Validation validation = new Validation();
		if (StringUtils.isEmpty(user.getName()))
			validation.add("Name is required field and cannot be empty");
		if (StringUtils.isEmpty(user.getFirstName()))
			validation.add("First Name is required field and cannot be empty");
		if (StringUtils.isEmpty(user.getRole()))
			validation.add("Role is required field and cannot be empty");
		if (StringUtils.isEmpty(user.getPassword()))
			validation.add("Password is required field and cannot be empty");
		if (validation.isNotValid()) {
			throw new ValidationException(validation);
		}
	}

}
