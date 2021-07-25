package com.maingocdieu.SportShop.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;


  @Entity
  @Table(name = "Category")
  @Data
  @EqualsAndHashCode(callSuper=false)
  public class Category extends BaseEntity {
   
    @Column(name="CODE")
    private String  code;
    
    @Column(name= "Name")
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Product> listProduct;
    
  }
