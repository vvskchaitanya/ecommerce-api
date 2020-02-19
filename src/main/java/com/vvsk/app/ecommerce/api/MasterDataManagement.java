package com.vvsk.app.ecommerce.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vvsk.app.ecommerce.dto.request.Request;
import com.vvsk.app.ecommerce.dto.response.Response;

@RestController
@RequestMapping("/manage")
public class MasterDataManagement {

	@PutMapping("/data")
	public ResponseEntity<Response> create(@RequestBody Request request) {
		return null;
	}

	@GetMapping("/data")
	public ResponseEntity<Response> read(@RequestParam String id) {
		return null;
	}

	@PostMapping("/data")
	public ResponseEntity<Response> update(@RequestBody Request request) {
		return null;
	}

	@DeleteMapping("/data")
	public ResponseEntity<Response> delete(@RequestParam String id) {
		return null;
	}

}
