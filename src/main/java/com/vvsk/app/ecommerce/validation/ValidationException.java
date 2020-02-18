package com.vvsk.app.ecommerce.validation;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 11111L;

	private Validation validation;

	public ValidationException(Validation validation) {
		super();
		this.validation = validation;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

}
