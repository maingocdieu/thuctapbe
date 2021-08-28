package com.maingocdieu.SportShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.SizeConverter;
import com.maingocdieu.SportShop.dto.SizeDto;
import com.maingocdieu.SportShop.entity.Size;
import com.maingocdieu.SportShop.repository.SizeRepository;
import com.maingocdieu.SportShop.service.ISizeService;


@Service
public class SizeServiceImpl implements ISizeService {

	
	@Autowired
	SizeRepository sizeRepository;
	
	 @Autowired
	 private SizeConverter sizeConverter;
	 
	 
	@Override
	public List<Size> findAllSize() {
		return sizeRepository.findAll();
	}

	@Override
	public Size insertSize(SizeDto sizeDto) {
		Size size = sizeConverter.converToEntity(sizeDto);
		return sizeRepository.save(size);
	}

	@Override
	public Size updateSize(long id, SizeDto sizeDto) {
		List<Size> sizes = sizeRepository.findAll();
		for (Size size : sizes) {
			if(size.getNamesize().equals(sizeDto.getNameSize())) {
				return null;
			}
		}
		Optional<Size> tempSize = sizeRepository.findById(id);
	    if (tempSize.isPresent()) {
	    	Size updateSize = tempSize.get();
	    	updateSize.setNamesize(sizeDto.getNameSize());
	    	return sizeRepository.save(updateSize);
	 
	    } else {
	      return null;
	    }
	}

	@Override
	public Boolean DeleteSize(Long id) {
		Size size = sizeRepository.findById(id).get();
		if(size.getListProductDetail().size() >0) {
			return false;
		} else {
			sizeRepository.deleteById(id);
			return true;
		}
	}

}
