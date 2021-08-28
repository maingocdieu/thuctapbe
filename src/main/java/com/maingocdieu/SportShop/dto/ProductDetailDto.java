package com.maingocdieu.SportShop.dto;

import lombok.Data;

@Data	
public class ProductDetailDto {
	Long colorId;
	Long productId;
	Long sizeId;
	Long supllierId; 
	float price;
}
