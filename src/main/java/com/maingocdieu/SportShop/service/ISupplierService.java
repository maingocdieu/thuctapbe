package com.maingocdieu.SportShop.service;

import java.util.List;

import com.maingocdieu.SportShop.dto.SupplierDto;
import com.maingocdieu.SportShop.entity.Supplier;

public interface ISupplierService {
	List<Supplier> findAllSupplier();
	Supplier insertSupplier(SupplierDto supplierDto);
	Supplier updateSupplier(long id, SupplierDto supplierDto);
	Boolean DeleteSupplier(Long id);
}
