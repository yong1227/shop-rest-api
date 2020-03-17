package com.simple.shop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.shop.domain.ProductVO;

@Repository
public interface ProductDetailDAO {

	List<ProductVO> findProductDetailById(Long productId);

}
