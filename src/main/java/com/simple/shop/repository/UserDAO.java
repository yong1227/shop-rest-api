package com.simple.shop.repository;

import org.springframework.stereotype.Repository;

import com.simple.shop.domain.TokenVO;
import com.simple.shop.domain.UserVO;

@Repository
public interface UserDAO {

	void insertUser(UserVO userVO);

	UserVO findUserById(Long userId);

	int findUserByEmail(String email);

	UserVO findUserByEmailAndPassword(UserVO userVO);

	void login(TokenVO tokenVO);

	TokenVO findTokenbyToken(String token);

}
