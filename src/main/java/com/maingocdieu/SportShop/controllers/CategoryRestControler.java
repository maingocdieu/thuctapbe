package com.maingocdieu.SportShop.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.entity.Category;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.service.ICategoryService;

@RestController()
@RequestMapping(path = "/api/category")
public class CategoryRestControler {
  @Autowired
  ICategoryService categoryService;
  
  @PostMapping()
  public ResponseEntity<?> insertUser(@RequestBody Category category) {
    categoryService.insertCategory(category);
    return ResponseEntity.ok(true);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
    Category currentCategory = categoryService.updateCategory(id, category);
    if (currentCategory == null) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(true);
  }
  
  @PostMapping("/delete")
  public ResponseEntity<?> deleteUser(@RequestBody List<Long> ids) {
	  System.out.println("dieu");
    Boolean temp = categoryService.deleteCategoryById(ids);
    if (Boolean.TRUE.equals(temp)) {
      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  @GetMapping()
  public List<Category> getAllCategory(){
    return categoryService.findAllCategory();
  }
  
  @PostMapping("/deleteCategory")
  public ResponseEntity<?> DeleteCategory(@RequestBody Long id) {
    return ResponseEntity.ok(categoryService.DeleteCateGory(id));
  }
}
