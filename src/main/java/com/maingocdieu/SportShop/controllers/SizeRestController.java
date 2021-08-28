package com.maingocdieu.SportShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.SizeDto;
import com.maingocdieu.SportShop.entity.Size;
import com.maingocdieu.SportShop.service.ISizeService;

@RestController()
@RequestMapping("/api/size")
public class SizeRestController {

	
	@Autowired
	  ISizeService sizeService;
	  

	 @GetMapping()
	  public List<Size> getAllSize(){
	    return sizeService.findAllSize();
	  }
	
	 
	 @PostMapping()
	  public ResponseEntity<?> insertSize(@RequestBody SizeDto size) {
		 sizeService.insertSize(size);
	    return ResponseEntity.ok(true);
	  }
	 
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<?> updateSize(@PathVariable("id") long id,@RequestBody SizeDto size) {
	    return ResponseEntity.ok(sizeService.updateSize(id, size));
	  }
	
	 
	 @PostMapping("/deleteSize")
	  public ResponseEntity<?> DeleteCategory(@RequestBody Long id) {
	    return ResponseEntity.ok(sizeService.DeleteSize(id));
	  }
}
