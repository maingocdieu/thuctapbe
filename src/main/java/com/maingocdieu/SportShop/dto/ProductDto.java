package com.maingocdieu.SportShop.dto;

import com.maingocdieu.SportShop.entity.Category;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends AbstractDTO<ProductDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean isDeleted;

	private String nameProduct;

	private String nameImage;

	private String thumNail;

	private Float giaBanRa;
	
	private Float soLuong;
	
	private String describe;
	
	private Float giaNhapVao;

	private String base64;

	public String categoryCode;

	private Category category;

	private Long categoryId;
}
