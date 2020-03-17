package com.simple.shop.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.ResultVO;
import com.simple.shop.domain.UserVO;
import com.simple.shop.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResultVO insertUser(@RequestBody UserVO userVO) throws ParseException {
		return userService.insertUser(userVO);
	}
	
	@GetMapping("/emailCheck")
	public ResultVO findUserByEmail(@RequestParam("email") String email ) {
		return userService.findUserByEmail(email);
	}
	
	@PostMapping("/auth")
	public ResultVO login(@RequestBody UserVO userVO) {
		return userService.login(userVO);
	}
}
