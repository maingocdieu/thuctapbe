package com.maingocdieu.SportShop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "T_Order")
@Data
@EqualsAndHashCode(callSuper=false)
public class Order extends BaseEntity {
 
  @Column
  private String diaChi;
  
  @Column
  private String sdt;
  
  @Column
  private float totalPrice;
  
  @Temporal(TemporalType.TIMESTAMP)
  Date publicationDate;

  @OneToMany(mappedBy = "product")
  List<OderDetail> listOderDetail=new ArrayList<OderDetail>();
  
  @ManyToOne
  @JoinColumn(name="user_id")
  User user;
  
}
