package com.example.CafeManagementSystem.restImpl;

import java.util.Iterator;
import java.util.Map;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeManagementSystem.constant.CafeConstant;
import com.example.CafeManagementSystem.rest.UserRest;
import com.example.CafeManagementSystem.service.UserService;
import com.example.CafeManagementSystem.utils.CafeUtils;

@RestController
public class UserRestImpl implements UserRest {

	@Autowired
	UserService userService;

	@Override
	public ResponseEntity<String> singup(Map<String, String> resquestMap) {
		try {
			return userService.singUp(resquestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstant.SOME_THINGWHENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
