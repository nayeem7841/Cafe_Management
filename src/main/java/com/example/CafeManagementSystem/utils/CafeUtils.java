package com.example.CafeManagementSystem.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

	public CafeUtils() {

	}

	public static ResponseEntity<String> getResponseEntity(String reponseMessage, HttpStatus httpStatus) {
		return new ResponseEntity<String>(reponseMessage, httpStatus);
	}

}
