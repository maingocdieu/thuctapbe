package com.maingocdieu.SportShop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.ProductDetailConverter;
import com.maingocdieu.SportShop.dto.ProductDetailDto;
import com.maingocdieu.SportShop.dto.ProductResponseDto;
import com.maingocdieu.SportShop.dto.UpdateProductDetailDto;
import com.maingocdieu.SportShop.entity.Color;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.ProductDetail;
import com.maingocdieu.SportShop.entity.Size;
import com.maingocdieu.SportShop.entity.Supplier;
import com.maingocdieu.SportShop.repository.ColorRepository;
import com.maingocdieu.SportShop.repository.ProductDetailRepository;
import com.maingocdieu.SportShop.repository.ProductReponsitory;
import com.maingocdieu.SportShop.repository.SizeRepository;
import com.maingocdieu.SportShop.repository.SupplierRepository;
import com.maingocdieu.SportShop.service.IProductDetail;

@Service
public class ProductDetailServiceImpl implements IProductDetail {

	@Autowired
	ProductReponsitory productRepository;

	@Autowired
	ColorRepository colorRepository;

	@Autowired
	SizeRepository sizeRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	ProductDetailRepository productDetailRepository;

	@Autowired
	ProductDetailConverter productDetailConverter;

	@Override
	public Boolean insert(List<ProductDetailDto> productdetaildto) {

		List<ProductDetail> listProductDetail = productDetailRepository.findAll();
		for (ProductDetailDto temp : productdetaildto) {
			for (ProductDetail productDetail : listProductDetail) {
				Long a = temp.getColorId();
				Long b = temp.getSupllierId();
				Long c = temp.getProductId();
				Long d = temp.getSizeId();
				if (productDetail.getColor().getId().equals(a) && productDetail.getSupplier().getId().equals(b)
						&& productDetail.getProduct().getId().equals(c) && productDetail.getSize().getId().equals(d)) {
					return false;
				}
			}
		}

		for (ProductDetailDto temp : productdetaildto) {
			Product prodduct = productRepository.findById(temp.getProductId()).get();
			Color color = colorRepository.findById(temp.getColorId()).get();
			Size size = sizeRepository.findById(temp.getSizeId()).get();
			Supplier supplier = supplierRepository.findById(temp.getSupllierId()).get();
			ProductDetail test = new ProductDetail();
			test.setSoLuong(0);
			test.setPrice(temp.getPrice());
			test.setColor(color);
			test.setProduct(prodduct);
			test.setSupplier(supplier);
			test.setSize(size);
			productDetailRepository.save(test);
		}
		return true;
	}

	@Override
	public List<ProductResponseDto> getProductDetail() {
		List<ProductDetail> productDetails = productDetailRepository.findAll();
		List<ProductResponseDto> a = new ArrayList<ProductResponseDto>();
		for (ProductDetail productDetail : productDetails) {
			productDetail.getProduct().setProductDetail(null);
			ProductResponseDto b = productDetailConverter.convertToDto(productDetail);
			a.add(b);
		}
		return a;
	}

	@Override
	public Boolean updateProductDetail(UpdateProductDetailDto update) {

		List<ProductDetail> listProductDetail = productDetailRepository.findAll();
		ProductDetail test = productDetailRepository.findById(update.getOldProducDetailtId()).get();
		Long a = update.getColorId();
		Long b = update.getSupllierId();
		Long c = update.getProductId();
		Long d = update.getSizeId();
		if (test.getColor().getId().equals(a) == false || test.getSupplier().getId().equals(b)  == false ||
				 test.getProduct().getId().equals(c) == false || test.getSize().getId().equals(d) == false) {
			for (ProductDetail productDetail : listProductDetail) {
				if (productDetail.getColor().getId().equals(a) && productDetail.getSupplier().getId().equals(b)
						&& productDetail.getProduct().getId().equals(c) && productDetail.getSize().getId().equals(d)) {
					return false;
				}
			}
		}
		Product prodduct = productRepository.findById(update.getProductId()).get();
		Color color = colorRepository.findById(update.getColorId()).get();
		Size size = sizeRepository.findById(update.getSizeId()).get();
		Supplier supplier = supplierRepository.findById(update.getSupllierId()).get();
		test.setPrice(update.getPrice());
		test.setColor(color);
		test.setProduct(prodduct);
		test.setSupplier(supplier);
		test.setSize(size);
		productDetailRepository.save(test);
		return true;
	}

	@Override
	public Boolean insertProductDetail(ProductDetailDto productdetaildto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteProductDetail(Long id) {
		ProductDetail productDetail = productDetailRepository.findById(id).get();
		
		if (productDetail.getGoodsReceivedNote().size() > 0) {
			return false;
		}
		if (productDetail.getOrderDetails().size() > 0) {
			return false;
		}

		productDetailRepository.deleteById(id);
		
		return true;
	}

}
