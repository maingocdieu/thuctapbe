package com.maingocdieu.SportShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.ColorConverter;
import com.maingocdieu.SportShop.dto.ColorDto;
import com.maingocdieu.SportShop.entity.Color;
import com.maingocdieu.SportShop.repository.ColorRepository;
import com.maingocdieu.SportShop.service.IColorService;


@Service
public class ColorServiceImpl implements IColorService {

	
	@Autowired
	ColorRepository colorRepository;
	
	 @Autowired
	  private ColorConverter colorConverter;

	@Override
	public List<Color> findAllColor() {
		return colorRepository.findAll();
	}

	@Override
	public Color insertColor(ColorDto colorDto) {
		Color color = colorConverter.converToEntity(colorDto);
		return colorRepository.save(color);
		
	}

	@Override
	public Color updateColor(long id, ColorDto colorDto) {
		
		List<Color> colors = colorRepository.findAll();
		for (Color color : colors) {
			if(color.getNameColor().equals(colorDto.getColorName())) {
				return null;
			}
		}
		
		Optional<Color> tempColor = colorRepository.findById(id);
	    if (tempColor.isPresent()) {
	    	Color updateColor = tempColor.get();
	    	updateColor.setNameColor(colorDto.getColorName());
	    	return colorRepository.save(updateColor);
	 
	    } else {
	      return null;
	    }
	}
	
	
	@Override
	public Boolean DeleteColor(Long id) {
		Color color = colorRepository.findById(id).get();
		
		System.out.println("czcz");
		if(color.getListProductDetail().size() >0) {
			return false;
		} else {
			colorRepository.deleteById(id);
			return true;
		}
	}

}
