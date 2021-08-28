package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.dto.ProductResponseDto;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.ProductDetail;


@Component
public class ProductDetailConverter {

	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductResponseDto convertToDto(ProductDetail productDetail) {
		ProductResponseDto result = modelMapper.map(productDetail, ProductResponseDto.class);
		return result;
	}
	
	public ProductDetail convertToEntity(ProductResponseDto productDto) {
		ProductDetail result = modelMapper.map(productDto, ProductDetail.class);
		return result;
	}
}
