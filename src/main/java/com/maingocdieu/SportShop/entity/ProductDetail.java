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

public class ProductDetail extends BaseEntity {

	
	@OneToOne()
	@JoinColumn(name = "idProduct")
	Product product;

	@OneToOne()
	@JoinColumn(name = "idSize")
	Size size;

	@OneToOne()
	@JoinColumn(name = "idColor")
	Color color;

	@OneToOne()
	@JoinColumn(name = "idNhaCungCap")
	Supplier supplier;

	@OneToMany(mappedBy = "productDetail")
	@JsonIgnore
	List<OderDetail> orderDetails = new ArrayList<OderDetail>();

	@OneToMany(mappedBy = "productDetail")
	@JsonIgnore
	private List<GoodsReceivedNoteDetail> goodsReceivedNote;

	int soLuong;
	float price;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public List<OderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public List<GoodsReceivedNoteDetail> getGoodsReceivedNote() {
		return goodsReceivedNote;
	}
	public void setGoodsReceivedNote(List<GoodsReceivedNoteDetail> goodsReceivedNote) {
		this.goodsReceivedNote = goodsReceivedNote;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
