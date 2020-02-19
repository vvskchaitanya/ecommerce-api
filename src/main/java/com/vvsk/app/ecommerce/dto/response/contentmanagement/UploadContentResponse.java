package com.vvsk.app.ecommerce.dto.response.contentmanagement;

import com.vvsk.app.ecommerce.dto.response.Response;
import com.vvsk.app.ecommerce.entity.Content;

public class UploadContentResponse extends Response {
	private long id;
	private String name;
	private String type;

	public UploadContentResponse(Content content) {
		super("Success");
		this.id = content.getId();
		this.name = content.getName();
		this.type = content.getType();
	}

}
