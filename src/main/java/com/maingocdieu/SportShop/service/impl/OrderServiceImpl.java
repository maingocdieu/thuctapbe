package com.maingocdieu.SportShop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.dto.CartDto;
import com.maingocdieu.SportShop.dto.OderDetailDto;
import com.maingocdieu.SportShop.entity.OderDetail;
import com.maingocdieu.SportShop.entity.Order;
import com.maingocdieu.SportShop.entity.ProductOrderId;
import com.maingocdieu.SportShop.repository.OderDetailRepository;
import com.maingocdieu.SportShop.repository.OderRepository;
import com.maingocdieu.SportShop.repository.ProductDetailRepository;
import com.maingocdieu.SportShop.repository.ProductReponsitory;
import com.maingocdieu.SportShop.repository.UserRepository;
import com.maingocdieu.SportShop.service.IOderService;

@Service
public class OrderServiceImpl implements IOderService {

	@Autowired
	OderDetailRepository oderDetailRepoitory;
	
	@Autowired
	OderRepository oderRepoitory;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	ProductReponsitory productRepository;
	
	@Autowired 
	ProductDetailRepository productDetailRepository;
	
	@Override
	public Order inserOder(OderDetailDto orderDto) {
		Order order = new Order();
		order.setDiaChi(orderDto.getOrder().getDiaChi());
		order.setSdt(orderDto.getOrder().getSdt());
		order.setHinhThucGiaoHang(orderDto.getOrder().getHinhthucgiaohang());
		order.setTotalPrice(orderDto.getOrder().getTotalPrice());
		order.setUser(userRepository.findById(orderDto.getUserId()).get());
		order.setTotalQuantity(orderDto.getOrder().getTotalQuantity());
		order.setListOderDetail(null);
		oderRepoitory.save(order);
		List<OderDetail> a = new ArrayList<OderDetail>();
		for (CartDto cartDto : orderDto.getCartItems()) {
			ProductOrderId composeKey = new ProductOrderId();
			composeKey.setOrderId(order.getId());
			composeKey.setProductDetailId(cartDto.getId());
			OderDetail oderDetail = new OderDetail();
			oderDetail.setProductOrderId(composeKey);
			oderDetail.setQuantity(cartDto.getQuantity());
			oderDetail.setOrder(order);
			oderDetail.setProductDetail(productDetailRepository.findById(cartDto.getId()).get());
			oderDetailRepoitory.save(oderDetail);
			a.add(oderDetail);
			
		}
		order.setListOderDetail(a);
		oderRepoitory.save(order);
		order.setListOderDetail(null);
		return order;
	}
	
	

	@Override
	public Order findById(Long id) {
		
		return oderRepoitory.findById(id).get() ;
	}

	@Override
	public List<Order> getAllOrder() {
		
		List<Order> listOrder = oderRepoitory.findAll();
		for (Order order : listOrder) {
			List<OderDetail> listOrderDetail =  order.getListOderDetail();
			
			for (OderDetail orderDetail : listOrderDetail) {
				orderDetail.getProductDetail().getProduct().setProductDetail(null);
			}
		}
		
		return listOrder;
	}



	@Override
	public Boolean UpdateStatus(com.maingocdieu.SportShop.dto.UpdateStatus updateStatus) {
		Order a = oderRepoitory.findById(updateStatus.getId()).get();
		a.setStatus(updateStatus.getStatus());
		oderRepoitory.save(a);
		return true;
	}



	@Override
	public Boolean DeleteOrder(Long id) {
		oderRepoitory.deleteById(id);
		return true;
	}



	@Override
	public Page<Order> getPageOrder(int page) {
		Pageable pageable = PageRequest.of(page, 3);
		 Page<Order> a = oderRepoitory.findAll(pageable);
		 
		 for (Order order : a) {
				List<OderDetail> listOrderDetail =  order.getListOderDetail();
				
				for (OderDetail orderDetail : listOrderDetail) {
					orderDetail.getProductDetail().getProduct().setProductDetail(null);
				}
			}
		return a;
	}

	
}
