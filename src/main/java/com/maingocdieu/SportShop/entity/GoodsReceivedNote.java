package com.maingocdieu.SportShop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GoodsReceivedNote")

public class GoodsReceivedNote extends BaseEntity {

	@Column
	private float totalPrice;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateWrite;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "goodsReceivedNote")
	private List<GoodsReceivedNoteDetail> products;

	@ManyToOne
	private User user;

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDateWrite() {
		return dateWrite;
	}

	public void setDateWrite(Date dateWrite) {
		this.dateWrite = dateWrite;
	}

	public List<GoodsReceivedNoteDetail> getProducts() {
		return products;
	}

	public void setProducts(List<GoodsReceivedNoteDetail> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
}
