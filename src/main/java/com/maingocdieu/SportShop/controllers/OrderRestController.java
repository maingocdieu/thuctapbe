package com.maingocdieu.SportShop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.NgayThongKeDto;
import com.maingocdieu.SportShop.dto.OderDetailDto;
import com.maingocdieu.SportShop.dto.ThongKe;
import com.maingocdieu.SportShop.dto.ThongKeLaiLo;
import com.maingocdieu.SportShop.repository.OderRepository;
import com.maingocdieu.SportShop.service.IOderService;

@RestController()
@RequestMapping("/api/product")
public class OrderRestController {
	
	  @Autowired
	  IOderService orderService;
	  
	  @Autowired
	  OderRepository oderRepository;
	  
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
	  
	  @PostMapping("/getOrderByUser")
	  public ResponseEntity<?> getOrderByUSer(@RequestBody Long id) {
			return ResponseEntity.ok(orderService.getOrderByUser(id)); 
		  }
	  
	  @PostMapping("/cancelOrder")
	  public ResponseEntity<?> cancelorder(@RequestBody Long id) {
			return ResponseEntity.ok(orderService.cancelOrder(id)); 
		  }
	  
	  @PostMapping("/thongke")
	  public ResponseEntity<?> getThongKe(@RequestBody NgayThongKeDto ngay) {
		  List<Object> a=  oderRepository.getThongKe(ngay.getNgayBatDau(), ngay.getNgayKetThuc());
		  List<ThongKe> b = new ArrayList<ThongKe>();
		  for (Object object : a) {
			  ThongKe c =new ThongKe();
			  c.setTongSoLuong((int) ((Object[])object)[0]);
			  c.setName((String) ((Object[])object)[1]);
			  c.setName_color((String) ((Object[])object)[2]);
			  c.setNamesize((String) ((Object[])object)[3]);
			  c.setName_supplier((String) ((Object[])object)[4]);
			  c.setPrice((Double) ((Object[])object)[5]);
			  b.add(c);
			 
		}
			return ResponseEntity.ok(b); 
		  }
	  
	  
	  @GetMapping("/thongkelailo")
	  public ResponseEntity<?> getThongKeLaiLo() {
		  List<Object> a=  oderRepository.getLaiLo();
		  List<ThongKeLaiLo> b = new ArrayList<ThongKeLaiLo>();
		  for (Object object : a) {
			  ThongKeLaiLo c =new ThongKeLaiLo();
			  c.setSoLuongNhap((int) ((Object[])object)[0]);
			  c.setGianhap((double) ((Object[])object)[1]);
			  c.setSoLuongTon((int) ((Object[])object)[2]);
			  c.setTongSoLuongBan((Integer) ((Object[])object)[3]);
			  c.setName((String) ((Object[])object)[4]);
			  c.setGiaBanRa((double) ((Object[])object)[5]);
			  c.setName_color((String) ((Object[])object)[6]);
			  c.setNamesize((String) ((Object[])object)[7]);
			  c.setName_supplier((String) ((Object[])object)[8]);
			 
			  b.add(c);
		}
		  
		  
			return ResponseEntity.ok(b); 
		  }
	  
}
