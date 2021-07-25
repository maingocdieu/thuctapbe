package com.maingocdieu.SportShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.service.IProductService;

import lombok.var;

@RestController()
@RequestMapping("/api/product")
public class ProductRestController {

  @Autowired
  IProductService productService;
  

  @PostMapping()
  public ResponseEntity<?> insertProducts(@RequestBody ProductDto productDto) {
    var rs = productService.insertProductDto(productDto);
    return ResponseEntity.ok(rs);
  }


  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategory(@PathVariable("id") long id,
      @RequestBody ProductDto productDto) {
    ProductDto currentProduct = productService.updateProduct(id, productDto);
    if (currentProduct == null) {
      return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(true);
  }

  @PostMapping("/delete")
  public ResponseEntity<User> deleteUser(@RequestBody List<Long> ids) {
    Boolean temp = productService.deleteProductById(ids);
    if (Boolean.TRUE.equals(temp)) {
      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOneProduct(@PathVariable("id") long id) {
    Product currentProduct = productService.findById(id);
    if (currentProduct == null) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(currentProduct);
  }

  @PostMapping("/test")
  public ResponseEntity<Page<Product>> finPagination(@RequestBody ProductDto productDto) {
    Page<Product> response = productService.findPageProduct(productDto);
    return new ResponseEntity<Page<Product>>(response, HttpStatus.OK);
  }
  
  @PostMapping("/getPageProduct")
  public ResponseEntity<Page<Product>> getPage(@RequestBody ProductDto productDto) {
    Page<Product> response = productService.findAll(productDto);
    return new ResponseEntity<Page<Product>>(response, HttpStatus.OK);
  }
  
  @GetMapping("/getPageProductCategory/{id}")
  	public ResponseEntity<Page<Product>> getPageCatgory(@PathVariable("id") Long id) {
    Page<Product> response = productService.findAllByCategorys(id);
    return new ResponseEntity<Page<Product>>(response, HttpStatus.OK);
  }
  
  
  
  
  
  
}
