package com.maingocdieu.SportShop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.entity.Product;

public interface IProductService {

  ProductDto updateProduct(long id, ProductDto productDto);

  Boolean deleteProductById(Long id);

  Product insertProduct(Product product);
  
  ProductDto insertProductDto(ProductDto productDto);
  
  Product findById(Long id);
  
  public Page<Product> findPageProduct(String keyword);
  
  public Page<Product> findAll(ProductDto productDto) ;
  
  public Page<Product> findAllByCategorys(Long id);
  
  public List<Product> findAll();
  
  Boolean updateStatus(Long id);
  
  public Page<Product> findProductShow(ProductDto productDto) ;
}
