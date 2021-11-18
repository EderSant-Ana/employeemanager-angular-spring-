package com.santana.employeemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyRegisteredException(String message) {
		super(message);
	}

	
	
}
