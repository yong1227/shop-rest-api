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
public class BannerVO {

	private Long id;
	private String content;
	private String createdAt;
}
