package com.maingocdieu.SportShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.OderDetailDto;
import com.maingocdieu.SportShop.service.IOderService;

@RestController()
@RequestMapping("/api/product")
public class OrderRestController {
	
	  @Autowired
	  IOderService orderService;
	  
	@PostMapping("/checkout")
	  public ResponseEntity<?> checkOutProductIsPhieuNhap(@RequestBody OderDetailDto orderDetailDto) {
		return ResponseEntity.ok(orderService.inserOder(orderDetailDto)); 
		  
	  }
	  
	  @GetMapping("/getDetailOder")
	  public ResponseEntity<?> getDetailOder() {
		return ResponseEntity.ok(orderService.findById(3L)); 
		  
	  }
	  
	  @GetMapping("/getAllOder")
	  public ResponseEntity<?> getAllOder() {
		return ResponseEntity.ok(orderService.getAllOrder()); 
		  
	  }
	  
	  @GetMapping("/getlistOrder/{page}")
	  public ResponseEntity<?> getPagePn(@PathVariable("page") int page) {
		return ResponseEntity.ok(orderService.getPageOrder(page)); 
	  }
}
