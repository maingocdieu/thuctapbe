package com.maingocdieu.SportShop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "T_Order")
@Data
@EqualsAndHashCode(callSuper=false)
public class Order extends BaseEntity {
 
  @Column(nullable = false)
  private String diaChi;
  
  @Column(nullable = false,columnDefinition = "nvarchar(255)" )
  private String sdt;
  
  @Column
  private float totalPrice;
  
  @Column
  private int totalQuantity;
  
  @Column
  private String hinhThucGiaoHang;
  
  @Column(columnDefinition = "bit default 0")
  private Boolean status;
  
  @OneToMany(mappedBy = "order")
  List<OderDetail> listOderDetail=new ArrayList<OderDetail>();
  
  @ManyToOne
  @JoinColumn(name="user_id")
  User user;
  
}
