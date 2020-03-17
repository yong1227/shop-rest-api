package com.simple.shop.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.ResultVO;
import com.simple.shop.domain.TokenVO;
import com.simple.shop.domain.UserVO;
import com.simple.shop.repository.BasketDAO;
import com.simple.shop.repository.ProductDAO;
import com.simple.shop.repository.UserDAO;
import com.simple.shop.util.RandomToken;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	public ResultVO insertUser(UserVO userVO) throws ParseException {

		String password = userVO.getPassword();
		String passwordConfirm = userVO.getPasswordConfirm();
		
		// 비밀번호 다를 경우
		if(!password.equals(passwordConfirm)) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", "비밀번호가 다릅니다.");
		}
		
		// 나이가 7세 이하인 경우 회원 가입 불가
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String nowDate = format.format(new Date());
		
		Date nowDate2 = format.parse(nowDate);
		Date birth = format.parse(userVO.getBirth());
		
		long diff = nowDate2.getTime() - birth.getTime();
		long diffDays = diff / (24*60*60*1000);
		long diffYears = (diffDays / 365);
		
		if(diffYears < 7) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", "나이가 7세미만입니다.");
		}
		
		// emailCheck
		String email = userVO.getEmail();
		int emailCheck = userDAO.findUserByEmail(email);
		if(emailCheck>0) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", " 가입된 이메일이 있습니다.");
		}
		
		userDAO.insertUser(userVO);
		
		Long userId = userVO.getId();
		userVO = userDAO.findUserById(userId);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", userVO);
	}
	
	public ResultVO findUserByEmail(String email) {
		
		int emailCheck = userDAO.findUserByEmail(email);
		
		if (emailCheck > 0) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", " 가입된 이메일이 있습니다.");	
		}else {
			return new ResultVO(HttpStatus.OK.value(), "Success", "가입가능한 이메일입니다.");
		}
	}

	public ResultVO login(UserVO userVO) {
		userVO = userDAO.findUserByEmailAndPassword(userVO);
		if(userVO == null) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", " 이메일 또는 비밀번호 정보가 맞지 않습니다.");	
		}
		Long userId = userVO.getId();
		
		String token = RandomToken.makeToken().toString();
		
		TokenVO tokenVO = new TokenVO();
		tokenVO.setToken(token);
		tokenVO.setUserId(userId);
		
		userDAO.login(tokenVO);
		
		tokenVO = userDAO.findTokenbyToken(token);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", tokenVO);
	}
}
