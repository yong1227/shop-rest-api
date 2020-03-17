package com.simple.shop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.shop.domain.BasketVO;

@Repository
public interface BasketDAO {

	List<BasketVO> findBasketByUserId(Long userId);

	BasketVO findBasketByBasketVO(BasketVO basketVO);

	void deleteBasketByProductId(Long userId, Long productId);

	void insertBasket(BasketVO basketVO);


}
