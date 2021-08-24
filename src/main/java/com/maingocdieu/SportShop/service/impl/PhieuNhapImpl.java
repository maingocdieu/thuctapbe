package com.maingocdieu.SportShop.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.dto.PhieuNhapDetailDto;
import com.maingocdieu.SportShop.dto.PhieuNhapDto;
import com.maingocdieu.SportShop.dto.UpdatePNDto;
import com.maingocdieu.SportShop.entity.GoodsReceivedNote;
import com.maingocdieu.SportShop.entity.GoodsReceivedNoteDetail;
import com.maingocdieu.SportShop.entity.Order;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.ProductNoteId;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.repository.ChiTietPhieuNhapRepository;
import com.maingocdieu.SportShop.repository.PhieuNhapRepository;
import com.maingocdieu.SportShop.repository.ProductReponsitory;
import com.maingocdieu.SportShop.repository.UserRepository;
import com.maingocdieu.SportShop.service.IPhieuNhapService;


@Service
public class PhieuNhapImpl implements IPhieuNhapService {

	@Autowired
	PhieuNhapRepository phieunhapRepository;
	
	@Autowired
	ChiTietPhieuNhapRepository chitietRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired 
	ProductReponsitory productRepository;
	
	
	
	public GoodsReceivedNote InsertPhieuNhap(PhieuNhapDto phieuNhapDto) {
		
			for (int i = 0; i < phieuNhapDto.getListPhieuNhap().size(); i++) {
		        for (int j = 0; j < phieuNhapDto.getListPhieuNhap().size(); j++) {
		            if (phieuNhapDto.getListPhieuNhap().get(i).getProductId() == phieuNhapDto.getListPhieuNhap().get(j).getProductId() && i != j) {
		                return null;
		            }
		        }
		    }
			float tongGia =  0f;
			GoodsReceivedNote phieuNhap =new  GoodsReceivedNote();
			phieuNhap.setDateWrite(phieuNhapDto.getNgayTao());
			phieuNhap.setProducts(null);
			for (PhieuNhapDetailDto listPhieuNhapDto : phieuNhapDto.getListPhieuNhap()) {
				tongGia = tongGia+ listPhieuNhapDto.getPrice()*listPhieuNhapDto.getAmount();
			}
			phieuNhap.setTotalPrice(tongGia);
			Optional<User> user = userRepository.findById(phieuNhapDto.getUserId());
			if(user.isPresent()) {
				phieuNhap.setUser(user.get());
			}
			phieunhapRepository.save(phieuNhap);
			for (PhieuNhapDetailDto listPhieuNhapDto : phieuNhapDto.getListPhieuNhap()) {
				ProductNoteId composeKey = new ProductNoteId();
				Optional<Product> product = productRepository.findById(listPhieuNhapDto.getProductId());
				product.get().setSoLuong(listPhieuNhapDto.getAmount() + product.get().getSoLuong());
				productRepository.save(product.get());
				GoodsReceivedNoteDetail goDetail = new GoodsReceivedNoteDetail();
				goDetail.setAmount(listPhieuNhapDto.getAmount().intValue());
				goDetail.setPrice(listPhieuNhapDto.getPrice().floatValue());
				composeKey.setGoodsNoteId(phieuNhap.getId());
				composeKey.setProductId(listPhieuNhapDto.getProductId());
				goDetail.setGoodsReceivedNote(phieuNhap);
				if(product.isPresent()) {
				goDetail.setProduct(product.get());
				}
				goDetail.setDeveloperProjectId(composeKey);
				chitietRepository.save(goDetail);
			
			}
			
			return phieuNhap;
	}
	
	
	
	public GoodsReceivedNote GetChiTietPhieuPhap(Long id) {
		
		return phieunhapRepository.findById(id).get() ;

	}



	@Override
	public GoodsReceivedNote updatePN(UpdatePNDto update) {
		GoodsReceivedNote a =  GetChiTietPhieuPhap(update.getId());
		for(GoodsReceivedNoteDetail b: a.getProducts()) {
			if(b.getDeveloperProjectId().getProductId() == update.getOldProductId()) {
				chitietRepository.delete(b);
				b.getDeveloperProjectId().setProductId(update.getProductId());
				b.setProduct(productRepository.findById(update.getProductId()).get());
				b.setAmount(update.getAmount());
				b.setPrice(update.getPrice());
				chitietRepository.save(b);
			}
		}
		return a;
	}

}
