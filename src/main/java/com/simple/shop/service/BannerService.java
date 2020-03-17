package com.simple.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.BannerVO;
import com.simple.shop.domain.ResultVO;
import com.simple.shop.repository.BannerDAO;

@Service
public class BannerService {
	@Autowired
	BannerDAO bannerDAO;
	
	public ResultVO findBanner() {
		List<BannerVO> bannerVO = bannerDAO.findBanner();
		return new ResultVO(HttpStatus.OK.value(), "Success", bannerVO);
	}
}
