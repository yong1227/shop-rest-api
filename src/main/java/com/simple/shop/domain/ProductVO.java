package com.simple.shop.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductVO {

	private Long id;
	private String name;
	private String content;
	private String price;
	private String salePrice;
	private String image;
	private String createdAt;
	@JsonInclude(value = Include.NON_EMPTY)
	private UserVO user;
	
}
