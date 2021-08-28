package com.maingocdieu.SportShop.dto;

import java.util.Date;
import java.util.List;

import com.maingocdieu.SportShop.entity.GoodsReceivedNoteDetail;
import com.maingocdieu.SportShop.entity.User;

import lombok.Data;


@Data
public class PhieuNhapResponseDto {

	private float totalPrice;

	
	Date dateWrite;

	
	private List<GoodsReceivedNoteDetail> products;


	private User user;
	
}
