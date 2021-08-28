package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.PhieuNhapResponseDto;
import com.maingocdieu.SportShop.entity.GoodsReceivedNote;


@Component
public class PhieuNhapResponseConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public PhieuNhapResponseDto convertToDto(GoodsReceivedNote goodsReceivedNote) {
		PhieuNhapResponseDto result = modelMapper.map(goodsReceivedNote, PhieuNhapResponseDto.class);
		return result;
	}
	
}
