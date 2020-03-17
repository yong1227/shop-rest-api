package com.simple.shop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.shop.domain.BannerVO;

@Repository
public interface BannerDAO {

	List<BannerVO> findBanner();

}
