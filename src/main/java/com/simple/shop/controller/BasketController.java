package com.simple.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.BasketVO;
import com.simple.shop.domain.ResultVO;
import com.simple.shop.service.BasketService;

@RestController
public class BasketController {

	@Autowired
	BasketService basketService;
	
	@GetMapping("/basket")
	public ResultVO findBasketByUserId(@CookieValue(value = "token", required = false) String token ) {
		return basketService.findBasketByUserId(token);
	}
	
	@PostMapping("/basket")
	public ResultVO insertBasket(@CookieValue (value = "token", required = false) String token, @RequestBody BasketVO basketVO) {
		
		return basketService.insertBasket(token, basketVO);
	}
	
	@DeleteMapping("/basket/{productId}")
	public ResultVO deleteBasketByProductId(@CookieValue(value = "token", required = false) String token ,  @PathVariable("productId") Long productId ) {
		return basketService.deleteBasketByProductId(token,productId);
	}
}
