package com.simple.shop.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDetailVO {

	private Long id;
	private Long productId;
	private String image;
	private String content;
	private Long order;
	private String createdAt;
	
}
