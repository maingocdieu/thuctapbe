package com.maingocdieu.SportShop.service;

import java.util.List;

import com.maingocdieu.SportShop.dto.SizeDto;
import com.maingocdieu.SportShop.entity.Size;

public interface ISizeService {

	List<Size> findAllSize();
	
	Size insertSize(SizeDto sizeDto);
	
	Size updateSize(long id, SizeDto sizeDto);
	Boolean DeleteSize(Long id);
}
