package com.example.CafeManagementSystem.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<String> singUp(Map<String, String> resquestMap);

}
