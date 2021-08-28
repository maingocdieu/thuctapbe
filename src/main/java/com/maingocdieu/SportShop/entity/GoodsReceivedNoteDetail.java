package com.maingocdieu.SportShop.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "GoodsReceivedNoteDetail")
@Data
public class GoodsReceivedNoteDetail {

	
	  	@EmbeddedId
	    private ProductNoteId productNoteId;
	 
	    @ManyToOne
	    @MapsId("productId")
	    private ProductDetail productDetail;
	 
	    @ManyToOne
	    @MapsId("goodsNoteId")
	    @JsonIgnore
	    private GoodsReceivedNote goodsReceivedNote;
	    
	    @Column
	    private Integer amount;
	    
	    @Column
	    private Float price;

		
	 
	    
}
