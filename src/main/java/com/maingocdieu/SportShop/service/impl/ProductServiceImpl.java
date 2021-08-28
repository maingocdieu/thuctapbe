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
import com.maingocdieu.SportShop.entity.ProductDetail;
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
    	
    	Product a = product.get();
    	
    	for (ProductDetail iterable_element : a.getProductDetail()) {
    		iterable_element.setProduct(null);
		}
      return product.get();
    } else {
      return null;
    }
  }


@Override
public Page<Product> findAll(ProductDto productDto) {
	Pageable pageable = PageRequest.of(productDto.getPage(), 20);
	
	Page<Product> pageProduct = productRespository.findAll(pageable);
	
	for (Product product : pageProduct) {
		
		for (ProductDetail productdetail : product.getProductDetail()) {
			productdetail.setProduct(null);
		}
		
	}
	return  pageProduct;
}

@Override
public Page<Product> findAllByCategorys(Long id) {
	Pageable pageable = PageRequest.of(0, 10);
	
	Page<Product> pageProduct =productRespository.findByCategoryId(id,pageable);
	for (Product product : pageProduct) {
		for (ProductDetail productdetail : product.getProductDetail()) {
			productdetail.setProduct(null);
		}
	}
	return pageProduct;
}

@Override
public List<Product> findAll() {
	List<Product> products = (List<Product>) productRespository.findAll();
	
	for (Product product : products) {
		product.setProductDetail(null);
	}
	return products;
}

@Override
public Page<Product> findPageProduct(String keyword) {
	Pageable pageable = PageRequest.of(0, 10);
	Page<Product> a =productRespository.findByNameProductContaining(keyword, pageable);
	
	for (Product product : a) {
		product.setProductDetail(null);
	}
	return a;
}




}
