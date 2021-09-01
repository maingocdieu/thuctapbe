package com.maingocdieu.SportShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.maingocdieu.SportShop.entity.Product;

public interface ProductReponsitory extends  PagingAndSortingRepository<Product, Long> {


  
  Page<Product> findByCategoryId(Long id, Pageable pageable);
  
  Page<Product> findByNameProductContaining( String name, Pageable pageable);
  
  
  

}
