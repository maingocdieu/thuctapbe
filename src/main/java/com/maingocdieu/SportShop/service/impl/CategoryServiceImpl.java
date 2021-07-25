package com.maingocdieu.SportShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.entity.Category;
import com.maingocdieu.SportShop.repository.CategoryRepository;
import com.maingocdieu.SportShop.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public List<Category> findByCategoryName(String categoryName) {
    return categoryRepository.findByName(categoryName);
  }

  @Override
  public Category updateCategory(long id, Category category) {
    Optional<Category> tempCategory = categoryRepository.findById(id);
    if (tempCategory.isPresent()) {
      Category updateCategory = tempCategory.get();
      updateCategory.setName(category.getName());
      updateCategory.setCode(category.getCode());
      categoryRepository.save(updateCategory);
      return updateCategory;
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteCategoryById(List<Long> ids) {
    for (long i : ids) {
      categoryRepository.deleteById(i);
    }
    return true;
  }

  @Override
  public Category insertCategory(Category category) {
    return categoryRepository.save(category);
  }


  @Override
  public List<Category> findAllCategory() {
    return (List<Category>) categoryRepository.findAll();
  }



}
