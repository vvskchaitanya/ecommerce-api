package com.vvsk.app.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vvsk.app.ecommerce.repository.ProductRepository;
import com.vvsk.app.ecommerce.validation.RequestValidator;

@RestController
@RequestMapping("/manage")
public class UserInterfaceManagement {

	@Autowired
	ProductRepository repository;

	@Autowired
	RequestValidator validator;

}
