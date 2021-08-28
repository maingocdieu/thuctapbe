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

import com.maingocdieu.SportShop.dto.ColorDto;
import com.maingocdieu.SportShop.entity.Color;
import com.maingocdieu.SportShop.service.IColorService;

@RestController()
@RequestMapping("/api/color")
public class ColorRestController {
	
	@Autowired
	  IColorService colorService;
	  

	 @GetMapping()
	  public List<Color> getAllCategory(){
	    return colorService.findAllColor();
	  }
	
	 
	 @PostMapping()
	  public ResponseEntity<?> insertColor(@RequestBody ColorDto color) {
		 colorService.insertColor(color);
	    return ResponseEntity.ok(true);
	  }
	 
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<?> updateColor(@PathVariable("id") long id,@RequestBody ColorDto color) {
	    return ResponseEntity.ok(colorService.updateColor(id, color));
	  }
	
	 
	 @PostMapping("/deleteColor")
	  public ResponseEntity<?> DeleteCategory(@RequestBody Long id) {
	    return ResponseEntity.ok(colorService.DeleteColor(id));
	  }
	
}
