package com.maingocdieu.SportShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.SupplierConverter;
import com.maingocdieu.SportShop.dto.SupplierDto;
import com.maingocdieu.SportShop.entity.Supplier;
import com.maingocdieu.SportShop.repository.SupplierRepository;
import com.maingocdieu.SportShop.service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService{

	
	@Autowired
	SupplierRepository supplierRepository;
	
	 @Autowired
	 private SupplierConverter supplierConverter;
	 
	@Override
	public List<Supplier> findAllSupplier() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier insertSupplier(SupplierDto supplierDto) {
		Supplier supplier = supplierConverter.converToEntity(supplierDto);
		return supplierRepository.save(supplier);
	}

	@Override
	public Supplier updateSupplier(long id, SupplierDto supplierDto) {
		List<Supplier> suppliers = supplierRepository.findAll();
		for (Supplier supplier : suppliers) {
			if(supplier.getNameSupplier().equals(supplierDto.getNameSupplier())) {
				return null;
			}
		}
		Optional<Supplier> tempSupplier = supplierRepository.findById(id);
	    if (tempSupplier.isPresent()) {
	    	Supplier updateSupplier = tempSupplier.get();
	    	updateSupplier.setNameSupplier(supplierDto.getNameSupplier());
	    	return supplierRepository.save(updateSupplier);
	 
	    } else {
	      return null;
	    }
	}

	@Override
	public Boolean DeleteSupplier(Long id) {
		Supplier supplier = supplierRepository.findById(id).get();
		if(supplier.getListProductDetail().size() >0) {
			return false;
		} else {
			supplierRepository.deleteById(id);
			return true;
		}
	}

}
