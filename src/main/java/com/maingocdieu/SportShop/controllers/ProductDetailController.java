package com.maingocdieu.SportShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.ProductDetailDto;
import com.maingocdieu.SportShop.dto.UpdateProductDetailDto;
import com.maingocdieu.SportShop.repository.ProductDetailRepository;
import com.maingocdieu.SportShop.service.IProductDetail;

@RestController()
@RequestMapping("/api/productdetail")
public class ProductDetailController {

	@Autowired
	ProductDetailRepository test;

	@Autowired
	IProductDetail productDetailImpl;

	@PostMapping()
	public ResponseEntity<?> insertSize(@RequestBody List<ProductDetailDto> productDetailDto) {
		return ResponseEntity.ok(productDetailImpl.insert(productDetailDto));
	}

	@GetMapping()
	public ResponseEntity<?> insertSizes() {
		return ResponseEntity.ok(productDetailImpl.getProductDetail());
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateProductDetail(@RequestBody UpdateProductDetailDto productDetailDto) {
		return ResponseEntity.ok(productDetailImpl.updateProductDetail(productDetailDto));
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteProductDetail(@RequestBody Long id) {
		return ResponseEntity.ok(productDetailImpl.deleteProductDetail(id));
	}
}
