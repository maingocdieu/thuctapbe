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

import com.maingocdieu.SportShop.dto.SupplierDto;
import com.maingocdieu.SportShop.entity.Supplier;
import com.maingocdieu.SportShop.service.ISupplierService;

@RestController()
@RequestMapping("/api/supplier")
public class SupplierRestController {

	
	@Autowired
	  ISupplierService supplierService;
	  

	 @GetMapping()
	  public List<Supplier> getAllCategory(){
	    return supplierService.findAllSupplier();
	  }
	
	 
	 @PostMapping()
	  public ResponseEntity<?> insertColor(@RequestBody SupplierDto supplier) {
		 supplierService.insertSupplier(supplier);
		 return ResponseEntity.ok(true);
	  }
	 
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<?> updateColor(@PathVariable("id") long id,@RequestBody SupplierDto supplier) {
	    return ResponseEntity.ok(supplierService.updateSupplier(id, supplier));
	  }
	
	 
	 @PostMapping("/deleteSupplier")
	  public ResponseEntity<?> DeleteCategory(@RequestBody Long id) {
	    return ResponseEntity.ok(supplierService.DeleteSupplier(id));
	  }
}
