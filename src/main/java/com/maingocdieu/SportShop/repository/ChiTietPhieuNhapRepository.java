package com.maingocdieu.SportShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maingocdieu.SportShop.entity.GoodsReceivedNoteDetail;
import com.maingocdieu.SportShop.entity.ProductNoteId;

public interface ChiTietPhieuNhapRepository extends JpaRepository<GoodsReceivedNoteDetail, ProductNoteId> {

	
}
