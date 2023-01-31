package com.ezen.allit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.allit.domain.Seller;
import com.ezen.allit.service.SellerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final SellerService sellerService;
	
	// 홈 화면 이동
	@GetMapping({"", "/"})
	public String index() {
		
		return "index";
	}
	
	// 판매자 입점신청 화면 이동
	@GetMapping("/sellerApply")
	public String applyView() {
		
		return "seller/apply";
	}
	
	// 판매자 로그인 화면 이동
	@GetMapping("/sellerLogin")
	public String loginView() {
		
		return "seller/login";
	}
	
	/*
	 * 판매자 입점신청
	 */
	@PostMapping("/sellerApply")
	public String apply(Seller seller) {
		sellerService.saveSeller(seller);
		
		return "redirect:/";
	}
}