package com.ezen.allit.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.allit.domain.CustomerCenter;

public interface CustomerCenterService {

	Page<CustomerCenter> getCustomercenter(Pageable pageable); 
	
//	void insertCustomerCenter(CustomerCenter cus);
	
	void insertCustomerCenter(CustomerCenter cus, MultipartFile imageFile) throws Exception;
	
	void updateCusto(CustomerCenter cus, MultipartFile imageFile) throws Exception;
	
	void deleteCusto(int cno);
	
	Page<CustomerCenter> findCustomerCenterByCategoryContaining(String cate, Pageable pageable);
	
	List<CustomerCenter> findCustomerCenterByPick(String pick);
}
