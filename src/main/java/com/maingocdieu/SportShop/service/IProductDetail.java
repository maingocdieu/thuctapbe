package com.maingocdieu.SportShop.service;

import java.util.List;

import com.maingocdieu.SportShop.dto.ProductDetailDto;
import com.maingocdieu.SportShop.dto.ProductResponseDto;
import com.maingocdieu.SportShop.dto.UpdateProductDetailDto;

public interface IProductDetail {

	
	Boolean insert(List<ProductDetailDto> productdetaildto);
	
	List<ProductResponseDto> getProductDetail();
	
	Boolean updateProductDetail(UpdateProductDetailDto update);
	
	Boolean insertProductDetail(ProductDetailDto productdetaildto);
	
	Boolean deleteProductDetail(Long id);
}
