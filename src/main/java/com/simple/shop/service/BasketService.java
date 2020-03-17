package com.simple.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.BasketVO;
import com.simple.shop.domain.ProductVO;
import com.simple.shop.domain.ResultVO;
import com.simple.shop.domain.TokenVO;
import com.simple.shop.repository.BasketDAO;
import com.simple.shop.repository.ProductDAO;
import com.simple.shop.repository.UserDAO;

@Service
public class BasketService {

	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDAO productDAO;
	
	public ResultVO insertBasket(String token, BasketVO basketVO) {
		TokenVO tokenVO = userDAO.findTokenbyToken(token);
		Long userId = tokenVO.getUserId();
		basketVO.setUserId(userId);
		
		basketDAO.insertBasket(basketVO);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", basketDAO.findBasketByUserId(userId));
	}
	
	public ResultVO findBasketByUserId(String token) {
		TokenVO tokenVO = userDAO.findTokenbyToken(token);
		Long userId = tokenVO.getUserId();
		
		List<ProductVO> productsByBasket =  productDAO.findProductAndBasketByUserId(userId);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", productsByBasket);
	}

	public ResultVO deleteBasketByProductId(String token, Long productId) {
		TokenVO tokenVO = userDAO.findTokenbyToken(token);
		Long userId = tokenVO.getUserId();
		
		basketDAO.deleteBasketByProductId(userId, productId);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", "delete Success");
	}
}
