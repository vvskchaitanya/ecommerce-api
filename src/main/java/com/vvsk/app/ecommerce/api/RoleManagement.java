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
import com.vvsk.app.ecommerce.entity.Role;
import com.vvsk.app.ecommerce.repository.RoleRepository;
import com.vvsk.app.ecommerce.validation.RequestValidator;
import com.vvsk.app.ecommerce.validation.ValidationException;

@RestController
@RequestMapping("/manage")
public class RoleManagement {

	@Autowired
	RoleRepository repository;

	@Autowired
	RequestValidator validator;

	@PutMapping("/role")
	public ResponseEntity<Role> create(@RequestBody Role newRole) throws ValidationException {

		Optional<Role> role = repository.findById(newRole.getName());
		if (role.isPresent())
			return new ResponseEntity<Role>(role.get(), HttpStatus.ALREADY_REPORTED);
		else {
			Role saved = repository.save(newRole);
			return new ResponseEntity<Role>(saved, HttpStatus.OK);
		}
	}

	@GetMapping("/role")
	public ResponseEntity<Role> read(@RequestParam(required = true) String name) {
		Optional<Role> role = repository.findById(name);
		if (role.isPresent())
			return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> list() {
		List<Role> roles = repository.findAll();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@DeleteMapping("/role")
	public ResponseEntity<Role> delete(@RequestParam(required = true) String name) {
		Optional<Role> role = repository.findById(name);
		if (role.isPresent()) {
			repository.delete(role.get());
			return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/role")
	public ResponseEntity<Role> update(@RequestBody Role updateRole) {
		Optional<Role> role = repository.findById(updateRole.getName());
		if (role.isPresent()) {
			repository.save(role.get());
			return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Role>(role.get(), HttpStatus.NOT_FOUND);
		}
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> handleValidationExceptions(ValidationException ve) {
		return new ResponseEntity<Response>(ve.getValidation(), HttpStatus.BAD_REQUEST);
	}

}
