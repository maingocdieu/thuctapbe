package com.maingocdieu.SportShop.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PhieuNhapDto {
	private Long pnId;
	private Long userId;
	private List<PhieuNhapDetailDto> listPhieuNhap;
	private Date ngayTao;	
}
