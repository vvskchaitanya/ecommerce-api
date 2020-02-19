package com.vvsk.app.ecommerce.api;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vvsk.app.ecommerce.dto.response.Response;
import com.vvsk.app.ecommerce.dto.response.contentmanagement.UploadContentResponse;
import com.vvsk.app.ecommerce.entity.Content;
import com.vvsk.app.ecommerce.repository.ContentRepository;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/content")
public class EnterpriseContentManagement {

	@Autowired
	ContentRepository repository;

	@PostMapping("/upload")
	public ResponseEntity<Response> upload(
			@ApiParam(name = "file", value = "Select the file to Upload", required = true) @RequestPart("file") MultipartFile file)
			throws IOException {
		if (file != null && file.getBytes().length > 0) {
			Content content = new Content(file.getName(), file.getContentType(), file.getBytes());
			content = repository.save(content);
			return new ResponseEntity<Response>(new UploadContentResponse(content), HttpStatus.OK);
		} else
			return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam Long id) {
		Optional<Content> content = repository.findById(id);
		if (content.isPresent())
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(content.get().getType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + content.get().getName() + "\"")
					.body(content.get().getData());
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
