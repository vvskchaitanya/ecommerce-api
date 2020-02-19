package com.vvsk.app.ecommerce.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vvsk.app.ecommerce.entity.Product;
import com.vvsk.app.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/manage")
public class ProductManagement {

	@Autowired
	ProductRepository repository;

	@PutMapping("/product")
	public ResponseEntity<Product> create(@RequestBody Product newProduct) {

		Optional<Product> product = repository.findByName(newProduct.getName());
		if (product.isPresent())
			return new ResponseEntity<Product>(product.get(), HttpStatus.ALREADY_REPORTED);
		else {
			Product saved = repository.save(newProduct);
			return new ResponseEntity<Product>(saved, HttpStatus.OK);
		}
	}

	@GetMapping("/product")
	public ResponseEntity<Product> read(@RequestParam(required = true) Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isPresent())
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		else {
			return new ResponseEntity<Product>(product.get(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/product")
	public ResponseEntity<Product> update(@RequestParam(required = true) Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isPresent()) {
			repository.delete(product.get());
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product.get(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/product")
	public ResponseEntity<Product> delete(@RequestBody Product updateProduct) {
		Optional<Product> product = repository.findById(updateProduct.getId());
		if (product.isPresent()) {
			repository.save(product.get());
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product.get(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<List<Product>> list() {
		List<Product> products = repository.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

}
