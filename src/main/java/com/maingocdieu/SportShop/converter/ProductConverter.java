package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.entity.Product;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDto convertToDto(Product product) {
		ProductDto result = modelMapper.map(product, ProductDto.class);
		return result;
	}
	
	public Product convertToEntity(ProductDto productDto) {
		Product result = modelMapper.map(productDto, Product.class);
		return result;
	}
}
