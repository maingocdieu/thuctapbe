package com.maingocdieu.SportShop.dto;

import com.maingocdieu.SportShop.entity.Color;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.Size;
import com.maingocdieu.SportShop.entity.Supplier;

import lombok.Data;


@Data
public class ProductResponseDto extends AbstractDTO<ProductResponseDto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	Product product;
	
	Size size;
	
	Color color ;
	
	Supplier supplier ;
	
	
}
