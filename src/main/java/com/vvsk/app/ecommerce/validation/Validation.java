package com.vvsk.app.ecommerce.validation;

import java.util.ArrayList;
import java.util.List;

import com.vvsk.app.ecommerce.dto.Response;

public class Validation implements Response {

	private boolean valid;

	private List<String> messages;

	public Validation() {
		this.clear();
	}

	public boolean isValid() {
		return this.valid;
	}

	public boolean isNotValid() {
		return !this.valid;
	}

	public void add(String message) {
		this.messages.add(message);
		this.valid();
	}

	public void clear() {
		this.messages = new ArrayList<String>();
		this.valid();
	}

	private void valid() {
		this.valid = this.messages.isEmpty();
	}

}
