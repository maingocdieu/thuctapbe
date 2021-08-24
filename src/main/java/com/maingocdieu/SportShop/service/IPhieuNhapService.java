package com.maingocdieu.SportShop.service;

import com.maingocdieu.SportShop.dto.PhieuNhapDto;
import com.maingocdieu.SportShop.dto.UpdatePNDto;
import com.maingocdieu.SportShop.entity.GoodsReceivedNote;
import com.maingocdieu.SportShop.entity.Order;

public interface IPhieuNhapService  {

	
	 GoodsReceivedNote  InsertPhieuNhap(PhieuNhapDto phieuNhapDto);
	 GoodsReceivedNote GetChiTietPhieuPhap(Long id);
	 GoodsReceivedNote updatePN(UpdatePNDto update);
}
