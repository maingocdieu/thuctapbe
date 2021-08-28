package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.SizeDto;
import com.maingocdieu.SportShop.entity.Size;

@Component
public class SizeConverter {
	@Autowired
	private ModelMapper modelMapper;

	public SizeDto convertToDto(Size size) {
		SizeDto result = modelMapper.map(size, SizeDto.class);
		return result;
	}
	
	public Size converToEntity(SizeDto sizeDto) {
		Size result = modelMapper.map(sizeDto, Size.class);
		return result;
	}
}
