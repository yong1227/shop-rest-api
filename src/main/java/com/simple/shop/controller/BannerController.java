package com.simple.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.ResultVO;
import com.simple.shop.service.BannerService;

@RestController
public class BannerController {

	@Autowired
	BannerService bannerService;
	
	@GetMapping("/banner")
	public ResultVO findBanner() {
		return bannerService.findBanner();
	}
}
