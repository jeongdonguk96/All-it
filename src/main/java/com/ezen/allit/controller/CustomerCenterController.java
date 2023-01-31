package com.ezen.allit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.allit.domain.CustomerCenter;
import com.ezen.allit.repository.CustomerCenterRepository;
import com.ezen.allit.service.CustomerCenterService;

@Controller
public class CustomerCenterController {
	
	@Autowired
	CustomerCenterService cusService;
	
	@Autowired
	CustomerCenterRepository cusRepo;
	
	@RequestMapping("/getCusto")
	public String getCusto(Model model) {
		
		List<CustomerCenter> custoList = cusService.getCustomercenter();
		
		model.addAttribute("custoList", custoList);
		
		return "customerCenter/customerCenter";
	}
	
	@RequestMapping("/insertCustoView")
	public String insertCustoView() {
		return "customerCenter/insertCusto";
	}
	
	@RequestMapping("/insertCusto")
	public String insertCusto(CustomerCenter cus) {
		
		cusService.insertCustomerCenter(cus);
		
		return "redirect:getCusto";
	}
	
	@RequestMapping("/findCusto")
	public String findCusto(String cate, Model model) {
		System.out.println("카테고리 번호 : "+cate);

		if(cate.equals("전체")) {
			cate = "자주하는질문";
		}else if(cate.equals("모두 보기")) {
			return "redirect:getCusto";
		}
		List<CustomerCenter> custoList = cusRepo.findCustomerCenterByCategoryContaining(cate);
		
		model.addAttribute("cateList", custoList);
		
		return "customerCenter/customerCenter";
	}
	
	@GetMapping("/getCustoDetail")
	public String getCustoDetail(int cno, Model model) {
		CustomerCenter custo = cusRepo.findCustomerCenterByCno(cno);
		
		model.addAttribute("custo", custo);
		
		return "/customerCenter/custoDetail";
	}
	
	@PostMapping("/updateCusto")
	public String updateCusto(CustomerCenter cus) {
		
		cusService.updateCusto(cus);
		
		return "redirect:getCusto";
	}
	
	@GetMapping("/deleteCusto")
	public String deleteCusto(int cno) {
		
		cusService.deleteCusto(cno);
		return "redirect:getCusto";
	}

}