package com.maingocdieu.SportShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.PhieuNhapDto;
import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.dto.UpdatePNDto;
import com.maingocdieu.SportShop.dto.UpdateStatus;
import com.maingocdieu.SportShop.entity.GoodsReceivedNote;
import com.maingocdieu.SportShop.entity.GoodsReceivedNoteDetail;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.ProductDetail;
import com.maingocdieu.SportShop.entity.ProductNoteId;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.payload.reponse.MessageResponse;
import com.maingocdieu.SportShop.repository.ChiTietPhieuNhapRepository;
import com.maingocdieu.SportShop.repository.PhieuNhapRepository;
import com.maingocdieu.SportShop.repository.ProductDetailRepository;
import com.maingocdieu.SportShop.service.IOderService;
import com.maingocdieu.SportShop.service.IPhieuNhapService;
import com.maingocdieu.SportShop.service.IProductService;

import lombok.var;

@RestController()
@RequestMapping("/api/product")
public class ProductRestController {

  @Autowired
  IProductService productService;
  
  @Autowired 
  IPhieuNhapService phieunhapSerice;
  

  @Autowired
  private PhieuNhapRepository phieuNhapRespository;
  
  @Autowired
  IOderService orderService;
  
  @Autowired
  PhieuNhapRepository pnRepository;
  
  @Autowired
  ChiTietPhieuNhapRepository ctpn;
  
	@Autowired
	ProductDetailRepository productDetailRepo;
	
  
  @PostMapping()
  public ResponseEntity<?> insertProducts(@RequestBody ProductDto productDto) {
    var rs = productService.insertProductDto(productDto);
    return ResponseEntity.ok(rs);
  }


  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategory(@PathVariable("id") long id,
      @RequestBody ProductDto productDto) {
    ProductDto currentProduct = productService.updateProduct(id, productDto);
    if (currentProduct == null) {
      return new ResponseEntity<ProductDto>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(true);
  }

  @PostMapping("/delete")
  public ResponseEntity<?> deleteUser(@RequestBody Long id) {
   return ResponseEntity.ok(productService.deleteProductById(id)) ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOneProduct(@PathVariable("id") long id) {
    Product currentProduct = productService.findById(id);
    if (currentProduct == null) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(currentProduct);
  }

  @PostMapping("/getPageProduct")
  public ResponseEntity<Page<Product>> getPage(@RequestBody ProductDto productDto) {
    Page<Product> response = productService.findAll(productDto);
    return new ResponseEntity<Page<Product>>(response, HttpStatus.OK);
  }
  
  @PostMapping("/getPageProductUser")
  public ResponseEntity<Page<Product>> getPageProduct(@RequestBody ProductDto productDto) {
    Page<Product> response = productService.findProductShow(productDto);
    return new ResponseEntity<Page<Product>>(response, HttpStatus.OK);
  }
  @GetMapping("/getPageProductCategory/{id}")
  	public ResponseEntity<Page<Product>> getPageCatgory(@PathVariable("id") Long id) {
    Page<Product> response = productService.findAllByCategorys(id);
    return new ResponseEntity<Page<Product>>(response, HttpStatus.OK);
  }
  
  @GetMapping("/getAll")
	public ResponseEntity< List<Product>> getAll() {
	  List<Product> response = productService.findAll();
	 return new ResponseEntity< List<Product>>(response, HttpStatus.OK);
}
  
  
  @PostMapping("/insertPhieuNhap")
  public ResponseEntity<?> insertaProducts(@RequestBody PhieuNhapDto phieuNhapDto) {
	 
	  GoodsReceivedNote a =  phieunhapSerice.InsertPhieuNhap(phieuNhapDto);
		  if (a== null) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("S???n ph???m ???? thu???c chi ti???t n??y"));
			}
		  return ResponseEntity.ok(a);
  }
  
  @GetMapping("/getPhieuNhapById/{id}")
  public ResponseEntity<?> getPhieuNhapById(@PathVariable("id") Long id) {
	  return ResponseEntity.ok(phieunhapSerice.GetChiTietPhieuPhapReponse(id));
  }	
  
  @GetMapping("/check/{id}")
  public ResponseEntity<?> checkProductIsPhieuNhap(@PathVariable("id") Long id) {
	return ResponseEntity.ok(phieuNhapRespository.demsoLUong(id)); 
	  
  }
  
  @GetMapping("/getPhieuNhap")
  public ResponseEntity<?> getPhieuNhap() {
	 List<GoodsReceivedNote> a =phieuNhapRespository.findAll();
	 for (GoodsReceivedNote goodsReceivedNote : a) {
		 goodsReceivedNote.setProducts(null);
	}
	return ResponseEntity.ok(a); 
	  
  }
  
  
  @PostMapping("/updatechitietpn")
  public ResponseEntity<?> updatechitietpn(@RequestBody UpdatePNDto update) {
	return ResponseEntity.ok(phieunhapSerice.updatePN(update)); 
	  
  }
  
  

  @PostMapping("/deletepn")
  public ResponseEntity<?> deletePn(@RequestBody Long id) {
	  GoodsReceivedNote a = phieuNhapRespository.findById(id).get();
	  if(a.getProducts().size() >0) {
		  return ResponseEntity.ok(false); 
	  }
	  else {
		  phieuNhapRespository.deleteById(id);
		  return ResponseEntity.ok(true); 
	  }
	 
  }
  
  
  @PostMapping("/deleteChiTietPn")
  public ResponseEntity<?> deleteChiTietPn(@RequestBody UpdatePNDto update) {
	  GoodsReceivedNote a =  phieunhapSerice.GetChiTietPhieuPhap(update.getId());
		for(GoodsReceivedNoteDetail b: a.getProducts()) {
			if(b.getProductNoteId().getProductId().equals(update.getOldProductId()) ) {
				ctpn.delete(b);
			}
		}
	  return ResponseEntity.ok(true); 
	  
  }
  
  @PostMapping("/insertChiTietPN")
  public ResponseEntity<?> insertctpn(@RequestBody UpdatePNDto update) {
	  	GoodsReceivedNoteDetail s = new GoodsReceivedNoteDetail();
	  	s.setAmount(update.getAmount());
	  	s.setPrice(update.getPrice());
	  	ProductNoteId m = new ProductNoteId();
	  	m.setGoodsNoteId(update.getId());
	  	m.setProductId(update.getProductId());
	  	s.setProductNoteId(m);
	  	GoodsReceivedNote a =  phieunhapSerice.GetChiTietPhieuPhap(update.getId());
	  	s.setGoodsReceivedNote(a);
	  	ProductDetail c = productDetailRepo.findById(update.getProductId()).get();
	  	c.setSoLuong(c.getSoLuong() + update.getAmount());
	  	productDetailRepo.save(c);
	  	s.setProductDetail(c);
	  	ctpn.save(s);
	  return ResponseEntity.ok(true); 
	  
  }
  
  
  @PostMapping("/updateStatus")
  public ResponseEntity<?> updateStatusOrder(@RequestBody UpdateStatus updateStatus ) {
	  return ResponseEntity.ok(orderService.UpdateStatus(updateStatus));
	  
  }
  
  
  
  @PostMapping("/deleteOrder")
  public ResponseEntity<?> deleteOrder(@RequestBody Long id ) {
	  return ResponseEntity.ok( orderService.DeleteOrder(id));
	  
  }
  
  @GetMapping("/search/{keyword}")
  public ResponseEntity<?> search(@PathVariable("keyword") String keyword) {
	return ResponseEntity.ok(productService.findPageProduct(keyword)); 
  }
  
  @GetMapping("/getlistpn/{page}")
  public ResponseEntity<?> getPagePn(@PathVariable("page") int page) {
	return ResponseEntity.ok(phieunhapSerice.getListPhieuNhap(page)); 
  }
  
  @PostMapping("/updateStatusProduct")
  public ResponseEntity<?> updateProduct(@RequestBody Long id ) {
	  return ResponseEntity.ok( productService.updateStatus(id));
	  
  }
}
