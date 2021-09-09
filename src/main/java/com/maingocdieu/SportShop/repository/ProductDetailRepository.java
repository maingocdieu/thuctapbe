package com.maingocdieu.SportShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maingocdieu.SportShop.entity.ProductDetail;

public interface ProductDetailRepository  extends JpaRepository<ProductDetail, Long>{
	
	

}
