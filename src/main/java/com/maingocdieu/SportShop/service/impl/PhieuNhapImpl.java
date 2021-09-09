package com.maingocdieu.SportShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.PhieuNhapResponseConverter;
import com.maingocdieu.SportShop.dto.PhieuNhapDetailDto;
import com.maingocdieu.SportShop.dto.PhieuNhapDto;
import com.maingocdieu.SportShop.dto.PhieuNhapResponseDto;
import com.maingocdieu.SportShop.dto.UpdatePNDto;
import com.maingocdieu.SportShop.entity.GoodsReceivedNote;
import com.maingocdieu.SportShop.entity.GoodsReceivedNoteDetail;
import com.maingocdieu.SportShop.entity.ProductDetail;
import com.maingocdieu.SportShop.entity.ProductNoteId;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.repository.ChiTietPhieuNhapRepository;
import com.maingocdieu.SportShop.repository.PhieuNhapRepository;
import com.maingocdieu.SportShop.repository.ProductDetailRepository;
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
	ProductDetailRepository productDetailRepository;
	
	@Autowired
	ProductReponsitory productRepository;

	@Autowired
	PhieuNhapResponseConverter pnConverter;

	public GoodsReceivedNote InsertPhieuNhap(PhieuNhapDto phieuNhapDto) {

		for (int i = 0; i < phieuNhapDto.getListPhieuNhap().size(); i++) {
			for (int j = 0; j < phieuNhapDto.getListPhieuNhap().size(); j++) {
				if (phieuNhapDto.getListPhieuNhap().get(i).getProductDetailId().equals( phieuNhapDto.getListPhieuNhap()
						.get(j).getProductDetailId()) && i != j) {
					return null;
				}
			}
		}
		float tongGia = 0f;
		GoodsReceivedNote phieuNhap = new GoodsReceivedNote();
		phieuNhap.setDateWrite(phieuNhapDto.getNgayTao());
		phieuNhap.setProducts(null);
		for (PhieuNhapDetailDto listPhieuNhapDto : phieuNhapDto.getListPhieuNhap()) {
			tongGia = tongGia + listPhieuNhapDto.getPrice() * listPhieuNhapDto.getAmount();
		}
		phieuNhap.setTotalPrice(tongGia);
		Optional<User> user = userRepository.findById(phieuNhapDto.getUserId());
		if (user.isPresent()) {
			phieuNhap.setUser(user.get());
		}
		phieunhapRepository.save(phieuNhap);
		for (PhieuNhapDetailDto listPhieuNhapDto : phieuNhapDto.getListPhieuNhap()) {
			ProductNoteId composeKey = new ProductNoteId();
			Optional<ProductDetail> product = productDetailRepository.findById(listPhieuNhapDto.getProductDetailId());
			product.get().setSoLuong(listPhieuNhapDto.getAmount() + product.get().getSoLuong());
			productDetailRepository.save(product.get());
			GoodsReceivedNoteDetail goDetail = new GoodsReceivedNoteDetail();
			goDetail.setAmount(listPhieuNhapDto.getAmount().intValue());
			goDetail.setPrice(listPhieuNhapDto.getPrice().floatValue());
			composeKey.setGoodsNoteId(phieuNhap.getId());
			composeKey.setProductId(listPhieuNhapDto.getProductDetailId());
			goDetail.setGoodsReceivedNote(phieuNhap);
			if (product.isPresent()) {
				goDetail.setProductDetail(product.get());
			}
			goDetail.setProductNoteId(composeKey);
			chitietRepository.save(goDetail);

		}

		return phieuNhap;
	}

	@Override
	public PhieuNhapResponseDto GetChiTietPhieuPhapReponse(Long id) {
		PhieuNhapResponseDto pndto = pnConverter.convertToDto(phieunhapRepository.findById(id).get());

		for (GoodsReceivedNoteDetail pnDetail : pndto.getProducts()) {

			
			pnDetail.getProductDetail().getProduct().setProductDetail(null);;
		}
		return pndto;
	}

	public GoodsReceivedNote GetChiTietPhieuPhap(Long id) {
		return phieunhapRepository.findById(id).get();
	}

	@Override
	public Boolean updatePN(UpdatePNDto update) {
		GoodsReceivedNote a = GetChiTietPhieuPhap(update.getId());
		for (GoodsReceivedNoteDetail b : a.getProducts()) {
			if (b.getProductNoteId().getProductId().equals(update.getOldProductId())) {
				chitietRepository.delete(b);
				b.getProductNoteId().setProductId(update.getProductId());
				ProductDetail productDetail = productDetailRepository.findById(update.getProductId()).get();
				productDetail.setSoLuong(productDetail.getSoLuong() - b.getAmount()+ update.getAmount());
				b.setProductDetail(productDetail);
				b.setAmount(update.getAmount());
				b.setPrice(update.getPrice());
				chitietRepository.save(b);
				return true;
			}
		}
		return false;
		
	}

	@Override
	public Page<GoodsReceivedNote> getListPhieuNhap(int soPage) {
		Pageable pageable = PageRequest.of(soPage, 3);
		Page<GoodsReceivedNote> a = phieunhapRepository.findAll(pageable);
		
		for (GoodsReceivedNote goodsReceivedNote : a) {
			 goodsReceivedNote.setProducts(null);
		}
		return a;
	}

}
