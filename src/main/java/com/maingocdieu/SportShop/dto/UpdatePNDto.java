package com.maingocdieu.SportShop.dto;

import lombok.Data;

@Data
public class UpdatePNDto {
	private Long id;
	private Long productId;
	private Long oldProductId;
	private int amount;
	private float price;
}
