package com.simple.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.ResultVO;
import com.simple.shop.repository.UserDAO;
import com.simple.shop.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	UserDAO userDAO;

	@GetMapping("/products")
	public ResultVO findProducts(@CookieValue(value = "token", required = false) String token, @RequestParam("morePage") Long morePage) {

		if (token == null) {
			return productService.findProducts(morePage);
		} else {
			return productService.findProductsAndUser(token, morePage);
		}
	}

	@GetMapping("/product/{productId}")
	public ResultVO findProductById(@CookieValue(value = "token", required = false) String token,
			@PathVariable("productId") Long productId) {
		
		if (token == null) {
			return productService.findProduct(productId);
		} else {
			return productService.findProductAndUser(token, productId);
		}
	}
	
	@GetMapping("/product/detail/{productId}")
	public ResultVO findProductDetailById(@PathVariable("productId") Long productId) {
		return productService.findProductDetailById(productId);
	}
}
