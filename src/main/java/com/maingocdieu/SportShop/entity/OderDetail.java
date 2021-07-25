package com.maingocdieu.SportShop.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "OderDetail")
@Data
public class OderDetail {
  @EmbeddedId
  private ProductOrderId productOrderId;
  
  @ManyToOne
  @MapsId("orderId")
  private Order order;

  @ManyToOne
  @MapsId("productId")
  private Product product;

  @Column
  private int quantity;
}
