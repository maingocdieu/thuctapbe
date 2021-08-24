package com.maingocdieu.SportShop.dto;

import java.util.List;

import lombok.Data;

@Data
public class OderDetailDto {
	List<CartDto> cartItems;
	OderDto order;
	private Long userId;
	
}
