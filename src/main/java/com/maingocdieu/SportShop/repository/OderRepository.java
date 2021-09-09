package com.maingocdieu.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maingocdieu.SportShop.dto.ThongKe;
import com.maingocdieu.SportShop.entity.Order;

public interface OderRepository extends JpaRepository<Order, Long> {

	@Query(value = "select * from t_order where t_order.user_id = :id" ,nativeQuery =true)
	List<Order> getOderByUserId(@Param("id") Long id);
	
	@Query(value = "select ctpr.TongSoLuong,  product.name,  color.name_color, size.namesize, supplier.name_supplier, ctpr.price from (select TongSoLuong, product_detail_id, product_detail.id_product, product_detail.id_color, product_detail.id_size, product_detail.id_nha_cung_cap, product_detail.price \r\n"
			+ "	from (select  sum(oder_detail.quantity) as 'TongSoLuong', oder_detail.product_detail_id from \r\n"
			+ "	(select * from t_order where t_order.created_date > :ngayBatDau and t_order.created_date < :ngayKetThuc and t_order.status is not null) a, oder_detail \r\n"
			+ " where a.id = oder_detail.order_id group by oder_detail.product_detail_id )as ct, product_detail where product_detail.id = ct.product_detail_id) ctpr, product,size,supplier,color where ctpr.id_product = product.id and ctpr.id_color = color.id and ctpr.id_size = size.id and ctpr.id_nha_cung_cap = supplier.id" ,nativeQuery =true)
	List<Object> getThongKe(@Param("ngayBatDau") String ngayBatDau, @Param("ngayKetThuc") String ngayKetThuc) ;
	
	
	@Query(value =" select ctg.soluongNhap,ctg.gianhap, ctg.so_luong as 'soluongton', ctg.tongsoluongban, product.name,ctg.price, color.name_color, size.namesize, supplier.name_supplier from (select * from   (select * from(select goods_received_note_detail.product_detail_id,sum(goods_received_note_detail.amount) as 'soluongNhap', avg(goods_received_note_detail.price) as'gianhap' from\r\n"
			+ "	(select * from goods_received_note) a, goods_received_note_detail\r\n"
			+ "	 where a.id = goods_received_note_detail.goods_received_note_id group by goods_received_note_detail.product_detail_id) d left join\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "(select  oder_detail.product_detail_id as 'test', sum(oder_detail.quantity) as 'tongsoluongban' from (select * from t_order where  t_order.status is not null ) dh, oder_detail where dh.id = oder_detail.order_id\r\n"
			+ "group by oder_detail.product_detail_id)\r\n"
			+ " e on e.test = d.product_detail_id) as cp, product_detail where cp.product_detail_id = product_detail.id ) ctg, product, color, size,supplier where ctg.id_product = product.id and ctg.id_color = color.id and ctg.id_size = size.id and ctg.id_nha_cung_cap = supplier.id\r\n"
			+ "  select ctg.soluongNhap,ctg.gianhap, ctg.so_luong as 'soluongton', ctg.tongsoluongban, product.name, color.name_color, size.namesize, supplier.name_supplier from (select * from   (select * from(select goods_received_note_detail.product_detail_id,sum(goods_received_note_detail.amount) as 'soluongNhap', avg(goods_received_note_detail.price) as'gianhap' from\r\n"
			+ "	(select * from goods_received_note) a, goods_received_note_detail\r\n"
			+ "	 where a.id = goods_received_note_detail.goods_received_note_id group by goods_received_note_detail.product_detail_id) d left join\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "(select  oder_detail.product_detail_id as 'test', sum(oder_detail.quantity) as 'tongsoluongban' from (select * from t_order where  t_order.status is not null ) dh, oder_detail where dh.id = oder_detail.order_id\r\n"
			+ "group by oder_detail.product_detail_id)\r\n"
			+ " e on e.test = d.product_detail_id) as cp, product_detail where cp.product_detail_id = product_detail.id ) ctg, product, color, size,supplier where ctg.id_product = product.id and ctg.id_color = color.id and ctg.id_size = size.id and ctg.id_nha_cung_cap = supplier.id\r\n"
			+ " ", nativeQuery = true)
		List<Object> getLaiLo();
}
