package com.maingocdieu.SportShop.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

  @Column(name = "Price", nullable = false)
  private Float price;

  @ManyToOne
  @JoinColumn(name = "category_id")
  @JsonIgnore
  private Category category;

  @OneToMany(mappedBy = "order")
  List<OderDetail> orderDetails = new ArrayList<OderDetail>();


}
