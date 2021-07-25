package com.maingocdieu.SportShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.maingocdieu.SportShop.entity.Product;

public interface ProductReponsitory extends  PagingAndSortingRepository<Product, Long> {
  @Query(
      value = "SELECT distinct u"
          + " FROM Product u"
          + "   JOIN u.category category"
          + " WHERE (:nameProduct is null OR u.nameProduct like :nameProduct)"
          + "  AND (:price is null OR u.price<=:price )"
          + "  AND (:category is null OR category.id = :category)")
  Page<Product> findAllUserWithPagination(@Param("nameProduct") String nameProduct,
      @Param("category") Long category, @Param("price") Float price, Pageable pageable);

  
  Page<Product> findByCategoryId(Long id, Pageable pageable);

}
