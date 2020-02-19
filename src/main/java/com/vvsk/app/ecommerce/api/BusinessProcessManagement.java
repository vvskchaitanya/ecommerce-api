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
@RequestMapping("/process")
public class BusinessProcessManagement {

	@PutMapping("/deploy")
	public ResponseEntity<Response> define(@RequestBody Request request) {
		return null;
	}

	@GetMapping("/start")
	public ResponseEntity<Response> start(@RequestParam String name) {
		return null;
	}

	@GetMapping("/cancel")
	public ResponseEntity<Response> cancel(@RequestParam String id) {
		return null;
	}

	@DeleteMapping("/undeploy")
	public ResponseEntity<Response> undeploy(@RequestParam String name) {
		return null;
	}

	@PostMapping("/list")
	public ResponseEntity<Response> list(@RequestBody Request request) {
		return null;
	}

}
