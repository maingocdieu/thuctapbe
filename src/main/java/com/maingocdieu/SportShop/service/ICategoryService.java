package com.maingocdieu.SportShop.service;

import java.util.List;

import com.maingocdieu.SportShop.entity.Category;

public interface ICategoryService {

  
  List<Category> findByCategoryName(String categoryName);

  Category updateCategory(long id, Category category);

  Boolean deleteCategoryById(List<Long> ids);

  Category insertCategory(Category category);

  List<Category> findAllCategory();
}
