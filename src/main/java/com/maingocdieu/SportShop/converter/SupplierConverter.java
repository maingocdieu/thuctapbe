package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.SupplierDto;
import com.maingocdieu.SportShop.entity.Supplier;

@Component
public class SupplierConverter {
	@Autowired
	private ModelMapper modelMapper;

	public SupplierDto convertToDto(Supplier supplier) {
		SupplierDto result = modelMapper.map(supplier, SupplierDto.class);
		return result;
	}
	
	public Supplier converToEntity(SupplierDto supplierDto) {
		Supplier result = modelMapper.map(supplierDto, Supplier.class);
		return result;
	}
}
