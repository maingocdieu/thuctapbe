package com.maingocdieu.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maingocdieu.SportShop.entity.GoodsReceivedNote;
import com.maingocdieu.SportShop.entity.Product;

public interface PhieuNhapRepository extends JpaRepository<GoodsReceivedNote, Long> {
	@Query(value = " select product.id, product.name  from  dbo.goods_received_note_detail fn  join dbo.product on fn.product_id = product.id and fn.goods_received_note_id = 25",nativeQuery = true)
	List<Object> listProduct( Long id);
	@Query(value = "select count(e.product_id)  from dbo.goods_received_note_detail  e where e.product_id = :id", nativeQuery = true)
	int demsoLUong(@Param("id") Long id);
	@Query(value ="select count(1) from dbo.goods_received_note_detail  where goods_received_note_detail.goods_received_note_id = :idPN and  goods_received_note_detail.product_id = :idP", nativeQuery = true)
	int kiemTraChiTietPhieuNhapTrung(@Param("idPN") Long idPN, @Param("idP") Long idP);	
	
}
