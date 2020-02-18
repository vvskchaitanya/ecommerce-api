package com.vvsk.app.ecommerce.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vvsk.app.ecommerce.dto.Response;
import com.vvsk.app.ecommerce.entity.User;
import com.vvsk.app.ecommerce.repository.UserRepository;
import com.vvsk.app.ecommerce.validation.RequestValidator;
import com.vvsk.app.ecommerce.validation.ValidationException;

@RestController
@RequestMapping("/user")
public class UserManagement {

	@Autowired
	UserRepository repository;

	@Autowired
	RequestValidator validator;

	@PutMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User newUser) throws ValidationException {
		validator.validateAddUser(newUser);
		Optional<User> user = repository.findById(newUser.getName());
		if (user.isPresent())
			return new ResponseEntity<User>(user.get(), HttpStatus.ALREADY_REPORTED);
		else {
			repository.save(newUser);
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<User> getUser(@RequestParam(required = true) String name) {
		Optional<User> user = repository.findById(name);
		if (user.isPresent())
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		else {
			return new ResponseEntity<User>(user.get(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/remove")
	public ResponseEntity<User> deleteUser(@RequestParam(required = true) String name) {
		Optional<User> user = repository.findById(name);
		if (user.isPresent()) {
			repository.delete(user.get());
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user.get(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User updateUser) {
		Optional<User> user = repository.findById(updateUser.getName());
		if (user.isPresent()) {
			repository.save(user.get());
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user.get(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> listUsers() {
		List<User> users = repository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> handleValidationExceptions(ValidationException ve) {
		return new ResponseEntity<Response>(ve.getValidation(), HttpStatus.BAD_REQUEST);
	}

}