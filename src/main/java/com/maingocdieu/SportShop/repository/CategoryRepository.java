package com.maingocdieu.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maingocdieu.SportShop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByName(String category);
  Category findOneByCode(String code);
}
