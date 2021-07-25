package com.maingocdieu.SportShop.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderId implements Serializable{

  private static final long serialVersionUID = 1L;

  @Column(name = "Product_id")
  private Long  productId;

  @Column(name = "order_id")
  private Long orderId;
}
