package com.maingocdieu.SportShop.service;

import com.maingocdieu.SportShop.dto.PhieuNhapDto;
import com.maingocdieu.SportShop.dto.PhieuNhapResponseDto;
import com.maingocdieu.SportShop.dto.UpdatePNDto;
import com.maingocdieu.SportShop.entity.GoodsReceivedNote;

public interface IPhieuNhapService  {

	
	 GoodsReceivedNote  InsertPhieuNhap(PhieuNhapDto phieuNhapDto);
	 GoodsReceivedNote GetChiTietPhieuPhap(Long id);
	 PhieuNhapResponseDto GetChiTietPhieuPhapReponse(Long id);
	 GoodsReceivedNote updatePN(UpdatePNDto update);
}
