package com.ezen.allit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.allit.domain.Product;
import com.ezen.allit.domain.Role;
import com.ezen.allit.domain.Seller;
import com.ezen.allit.service.SellerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
	private final SellerService sellerService;
		
	// 판매자 상품등록 화면 이동
	@GetMapping("/product/insert")
	public String insertView() {
		
		return "seller/insert";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	/*
	 * 판매자 로그인
	 */
	@PostMapping("/login")
	public String login(Seller seller, HttpSession session) {
		Seller theSeller = sellerService.findByIdAndPwd(seller.getId(), seller.getPwd());
		if(theSeller != null) {
			if(theSeller.getRole().equals(Role.ADMIN)) {
				session.setAttribute("seller", theSeller);
				return "admin/adminMain";
			}
			session.setAttribute("seller", theSeller);
			System.out.println("컨트롤러 로그인 처리 중 seller = " + theSeller);
			return "redirect:/seller/";
		} else {
			
			return "seller/login";
		}
	}
	
	/*
	 * 판매자 메인화면 이동
	 */
	@RequestMapping("/")
	public String mainView(Model model, @PageableDefault(page = 1) Pageable pageable,
									String searchKeyword,
									HttpSession session) {
		Seller seller = (Seller) session.getAttribute("seller");
		System.out.println("컨트롤러 메인화면 처리1 중 seller = " + seller);
		Page<Product> productList = null;
		if(searchKeyword == null) {
			productList = sellerService.getProductList(pageable, seller);
		} else {
			productList = sellerService.search(seller, searchKeyword, pageable);
		}
		System.out.println("컨트롤러 메인화면 처리3 중 productList = " + productList);
		
		int naviSize = 10; // 페이지네이션 갯수
		int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / naviSize))) - 1) * naviSize + 1; // 1 11 21 31 ~~
	    int endPage = ((startPage + naviSize - 1) < productList.getTotalPages()) ? startPage + naviSize - 1 : productList.getTotalPages();
		
	    model.addAttribute("productList", productList);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
		
		return "seller/main";
	}
	
	/*
	 * 판매자 상품조회
	 */
	@GetMapping("/product/{pno}")
	public String getProduct(@PathVariable int pno, Model model,
							@PageableDefault(page = 1) Pageable pageable) {
		Product theProduct = sellerService.getProduct(pno);
		model.addAttribute("product", theProduct);
		model.addAttribute("page", pageable.getPageNumber());
		
		return "seller/detail";
	}
	
	/*
	 * 판매자 상품등록
	 */
	@PostMapping("/product/insert")
	public String insertProduct(Product product, MultipartFile imageFile) throws Exception {
		product.setMdPickyn("n");
		sellerService.saveProduct(product, imageFile);
		
		return "redirect:/seller/";
	}
	
	/*
	 * 판매자 상품수정
	 */
	@PostMapping("/product/modify")
	public String modifyProduct(int pno, Product product, MultipartFile imageFile) throws Exception {
		sellerService.modifyProduct(pno, product, imageFile);
		
		return "redirect:/seller/";
	}

}











