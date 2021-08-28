package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.ColorDto;
import com.maingocdieu.SportShop.entity.Color;


@Component
public class ColorConverter {
	@Autowired
	private ModelMapper modelMapper;

	public ColorDto convertToDto(Color color) {
		ColorDto result = modelMapper.map(color, ColorDto.class);
		return result;
	}
	
	public Color converToEntity(ColorDto colorDto) {
		Color result = modelMapper.map(colorDto, Color.class);
		return result;
	}
}
