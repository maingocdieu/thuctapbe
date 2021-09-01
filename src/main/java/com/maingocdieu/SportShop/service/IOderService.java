package com.maingocdieu.SportShop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.maingocdieu.SportShop.dto.OderDetailDto;
import com.maingocdieu.SportShop.dto.UpdateStatus;
import com.maingocdieu.SportShop.entity.Order;

public interface IOderService {

	Order inserOder(OderDetailDto orderDto);
	
	Order findById(Long id);
	
	List<Order> getAllOrder();
	
	Boolean UpdateStatus(UpdateStatus updateStatus);
	
	Boolean DeleteOrder(Long id);
	
	Page<Order> getPageOrder(int page);
	
	
}
