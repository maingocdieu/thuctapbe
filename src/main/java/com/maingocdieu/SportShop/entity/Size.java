package com.maingocdieu.SportShop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Size extends BaseEntity {
	
	 @Column(columnDefinition = "nvarchar(200)",nullable = false)
	String namesize;

	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
	@JsonIgnore
	List<ProductDetail> listProductDetail;
}
