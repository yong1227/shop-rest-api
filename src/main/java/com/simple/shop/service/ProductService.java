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
import com.simple.shop.repository.ProductDetailDAO;
import com.simple.shop.repository.UserDAO;

@Service
public class ProductService {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	BasketDAO basketDAO;
	@Autowired
	ProductDetailDAO productDetailDAO;

	public ResultVO findProducts(Long morePage) {
		List<ProductVO> products = productDAO.findProducts(morePage);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", products);
	}

	public ResultVO findProductsAndUser(String token, Long morePage) {
		TokenVO tokenVO = userDAO.findTokenbyToken(token);
		Long userId = tokenVO.getUserId();
		List<ProductVO> products = productDAO.findProductsAndUser(userId, morePage);
		for (ProductVO product : products) {
			Long productId = product.getId();
			
			List<BasketVO> baskets = basketDAO.findBasketByUserId(userId);
			if(baskets.size()==0) {
				product.getUser().setIsBasket(false);
			}else {
				for (BasketVO basketVO : baskets) {
					Long productIdByBasket = basketVO.getProductId();
					if(productId == productIdByBasket) {
						product.getUser().setIsBasket(true);
						break;
					}else {
						product.getUser().setIsBasket(false);
					}
				}
			}
		}
		
		return new ResultVO(HttpStatus.OK.value(), "Success", products);
	}

	public ResultVO findProduct(Long productId) {
		return new ResultVO(HttpStatus.OK.value(), "Success", productDAO.findProductById(productId));
	}

	public ResultVO findProductAndUser(String token, Long productId) {
		TokenVO tokenVO = userDAO.findTokenbyToken(token);
		Long userId = tokenVO.getUserId();
		
		ProductVO productAndUser = productDAO.findProductAndUserByProductIdAndUserId(userId, productId);
		
		BasketVO basketVO = new BasketVO();
		basketVO.setProductId(productId);
		basketVO.setUserId(userId);
		
		basketVO = basketDAO.findBasketByBasketVO(basketVO);

		if(basketVO == null ) {
			productAndUser.getUser().setIsBasket(false);
		}else {
			productAndUser.getUser().setIsBasket(true);
		}
		
		return new ResultVO(HttpStatus.OK.value(), "Success", productAndUser);
	}

	public ResultVO findProductDetailById(Long productId) {
		List<ProductVO> productDetails = productDetailDAO.findProductDetailById(productId);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", productDetails);
	}
}
