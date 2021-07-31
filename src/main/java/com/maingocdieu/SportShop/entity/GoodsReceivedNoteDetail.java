package com.maingocdieu.SportShop.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GoodsReceivedNoteDetail")

public class GoodsReceivedNoteDetail {

	
	  	@EmbeddedId
	    private ProductNoteId developerProjectId;
	 
	    @ManyToOne
	    @MapsId("productId")
	    private Product product;
	 
	    @ManyToOne
	    @MapsId("goodsNoteId")
	    @JsonIgnore
	    private GoodsReceivedNote goodsReceivedNote;
	    
	    @Column
	    private Integer amount;
	    
	    @Column
	    private Float price;

		public ProductNoteId getDeveloperProjectId() {
			return developerProjectId;
		}

		public void setDeveloperProjectId(ProductNoteId developerProjectId) {
			this.developerProjectId = developerProjectId;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public GoodsReceivedNote getGoodsReceivedNote() {
			return goodsReceivedNote;
		}

		public void setGoodsReceivedNote(GoodsReceivedNote goodsReceivedNote) {
			this.goodsReceivedNote = goodsReceivedNote;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Float getPrice() {
			return price;
		}

		public void setPrice(Float price) {
			this.price = price;
		}
	    
	    
	 
	    
}
