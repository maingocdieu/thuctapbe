package com.maingocdieu.SportShop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Product")
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {

  @Column(name = "Name",columnDefinition = "nvarchar(200)",nullable = false)
  private String nameProduct;

  @Column(name = "ImageName")
  private String nameImage;
  
  @Column(name = "Thumnail")
  private String thumNail;
  
  @Column(name = "describe",columnDefinition = "ntext")
  private String describe;

  @Column(name = "Price", nullable = false)
  private Float giaBanRa;

  @Column(name = "SoLuong",columnDefinition = "integer default 0")
  private Integer soLuong;
  
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  
  
  @OneToMany(cascade= CascadeType.ALL)
  @JoinColumn(name="idProduct")
  List<ProductDetail> productDetail;

}
