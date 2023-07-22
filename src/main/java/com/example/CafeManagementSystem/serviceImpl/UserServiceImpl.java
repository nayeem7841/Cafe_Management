package com.example.CafeManagementSystem.serviceImpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.CafeManagementSystem.constant.CafeConstant;
import com.example.CafeManagementSystem.dao.UserDao;
import com.example.CafeManagementSystem.pojo.User;
import com.example.CafeManagementSystem.service.UserService;
import com.example.CafeManagementSystem.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<String> singUp(Map<String, String> resquestMap) {
		//log.info("Inside sign up {}",resquestMap);
		try {
			if (vaildSignUpMap(resquestMap)) {
				Optional<User> user = userDao.findByEmailId(resquestMap.get("email"));
				if (!user.isPresent()) {
					userDao.save(getUserFromMap(resquestMap));
					return CafeUtils.getResponseEntity(CafeConstant.USER_CREATED,HttpStatus.OK);
				} else {
					return CafeUtils.getResponseEntity(CafeConstant.DATA_EXISTS, HttpStatus.BAD_REQUEST);
				}
			} else {
				return CafeUtils.getResponseEntity(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return CafeUtils.getResponseEntity(CafeConstant.SOME_THINGWHENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public boolean vaildSignUpMap(Map<String, String> resquestMap) {
		if (resquestMap.containsKey("name") && resquestMap.containsKey("contactNumber")
				&& resquestMap.containsKey("email") && resquestMap.containsKey("password")) {
			return true;
		}
		return false;
	}

	public User getUserFromMap(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		return user;

	}

}
