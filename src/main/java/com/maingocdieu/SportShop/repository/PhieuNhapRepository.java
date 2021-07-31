package com.maingocdieu.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maingocdieu.SportShop.entity.GoodsReceivedNote;
import com.maingocdieu.SportShop.entity.Product;

public interface PhieuNhapRepository extends JpaRepository<GoodsReceivedNote, Long> {
//
//	@Query(value="SELECT  u"
////	        + " FROM t_User u"
////	        + "  JOIN u.roles role"
////	        + " WHERE (:userName is null OR u.userName like :userName)"
////	        + "  AND (:#{#roles[0]} = -1L OR role.id in :roles)"
////	        + "  AND (:email is null OR u.email like :email )"
////	        + "  AND (:address is null OR u.address like :address) ORDER BY id", countQuery = "SELECT count(*) FROM t_User", 
////	        nativeQuery = true)
//	
//	GoodsReceivedNote
	@Query(value = " select product.id, product.name  from  dbo.goods_received_note_detail fn  join dbo.product on fn.product_id = product.id and fn.goods_received_note_id = 25",nativeQuery = true)
	List<Product> listProduct( Long id);
}
