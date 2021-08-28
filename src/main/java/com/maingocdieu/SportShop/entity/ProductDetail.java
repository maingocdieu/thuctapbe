package com.maingocdieu.SportShop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class ProductDetail extends BaseEntity {

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProduct")
	Product product;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSize")
	Size size;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idColor")
	Color color;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idNhaCungCap")
	Supplier supplier;

	@OneToMany(mappedBy = "order")
	@JsonIgnore
	List<OderDetail> orderDetails = new ArrayList<OderDetail>();

	@OneToMany(mappedBy = "productDetail")
	@JsonIgnore
	private List<GoodsReceivedNoteDetail> goodsReceivedNote;

	int soLuong;
	float price;
	
}
