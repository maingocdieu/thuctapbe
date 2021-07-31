package com.maingocdieu.SportShop.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.ProductConverter;
import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.entity.Category;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.file.util.UploadFileUtils;
import com.maingocdieu.SportShop.repository.CategoryRepository;
import com.maingocdieu.SportShop.repository.ProductReponsitory;
import com.maingocdieu.SportShop.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private ProductReponsitory productRespository;
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  
  @Autowired
  private ProductConverter productConverter;

  @Override
  public ProductDto updateProduct(long id, ProductDto productDto) {
    Optional<Product> existProduct = productRespository.findById(id);
    if (existProduct.isPresent()) {
      Product product = existProduct.get();
      product.setNameProduct(productDto.getNameProduct());
      product.setGiaBanRa(productDto.getGiaBanRa());
      Category category = categoryRepository.findOneByCode(productDto.getCategoryCode());
      product.setCategory(category);
      if (productDto.getBase64() != null) {
        String base64Image = productDto.getBase64().split(",")[1];
        byte[] decodeBase64 = Base64.getDecoder().decode(base64Image.getBytes());
        UploadFileUtils.writeOrUpdate(decodeBase64, "/thumbnail/" + productDto.getNameImage());
        product.setThumNail("D:/thumbnail/" + productDto.getNameImage());
        product.setNameImage(productDto.getNameImage());
      }
      productRespository.save(product);
      return productConverter.convertToDto(product);
    } else
      return null;
  }

  @Override
  public Product insertProduct(Product product) {
    productRespository.save(product);
    return product;
  }



  @Override
  public ProductDto insertProductDto(ProductDto productDto) {
    Product product = (Product) productConverter.convertToEntity(productDto);
    Category category = categoryRepository.findOneByCode(productDto.getCategoryCode());
    product.setCategory(category);
    product.setSoLuong(0);
    if (productDto.getBase64() != null) {
      String base64Image = productDto.getBase64().split(",")[1];
      byte[] decodeBase64 = Base64.getDecoder().decode(base64Image.getBytes());
      UploadFileUtils.writeOrUpdate(decodeBase64, "/thumbnail/" + productDto.getNameImage());
      product.setThumNail("D:/thumbnail/" + productDto.getNameImage());
    }
    return productConverter.convertToDto(productRespository.save(product));
  }

  @Override
  public Boolean deleteProductById(List<Long> ids) {
    if (ids != null) {
      for (Long l : ids) {
        productRespository.deleteById(l);
      }
    }
    return true;
  }

  @Override
  public Product findById(Long id) {
    Optional<Product> product = productRespository.findById(id);
    if (product.isPresent()) {
      return product.get();
    } else {
      return null;
    }
  }

//  @Override
//  public Page<Product> findPageProduct(ProductDto productDto) {
//    Pageable firstPageWithTwoElements = PageRequest.of(productDto.getPage(), 10);
//    return productRespository.findAllUserWithPagination(productDto.getNameProduct(),productDto.getCategoryId(),productDto.getGiaBanRa(),firstPageWithTwoElements);
//  }

@Override
public Page<Product> findAll(ProductDto productDto) {
	Pageable pageable = PageRequest.of(productDto.getPage(), 10);
	return  productRespository.findAll(pageable);
}

@Override
public Page<Product> findAllByCategorys(Long id) {
	Pageable pageable = PageRequest.of(0, 10);
	return productRespository.findByCategoryId(id,pageable);
}

@Override
public List<Product> findAll() {
	
	return (List<Product>) productRespository.findAll();
}

@Override
public Page<Product> findPageProduct(ProductDto productDto) {
	// TODO Auto-generated method stub
	return null;
}

}
