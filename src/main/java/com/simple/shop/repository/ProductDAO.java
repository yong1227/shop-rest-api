package com.simple.shop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.shop.domain.ProductVO;

@Repository
public interface ProductDAO {

	List<ProductVO> findProducts(Long morePage);

	List<ProductVO> findProductsAndUser(Long userId, Long morePage);

	ProductVO findProductById(Long productId);

	ProductVO findProductAndUserByProductIdAndUserId(Long userId, Long productId);

	List<ProductVO> findProductAndBasketByUserId(Long userId);
}
