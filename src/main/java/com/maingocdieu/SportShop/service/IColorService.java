package com.maingocdieu.SportShop.service;

import java.util.List;

import com.maingocdieu.SportShop.dto.ColorDto;
import com.maingocdieu.SportShop.entity.Color;

public interface IColorService {

	
	List<Color> findAllColor();
	
	Color insertColor(ColorDto color);
	
	Color updateColor(long id, ColorDto color);
	Boolean DeleteColor(Long id);
}
