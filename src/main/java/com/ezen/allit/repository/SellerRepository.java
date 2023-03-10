package com.ezen.allit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.allit.domain.Role;
import com.ezen.allit.domain.Seller;

public interface SellerRepository extends JpaRepository<Seller, String> {

	// 판매자 로그인
	Seller findByIdAndPwd(String id, String pwd);
	
//	Optional<Seller> findById(String id);
	
	// 판매자별 검색 후 조회
//	Page<Seller> findByNameContaining(String id, String searchKeyword, PageRequest pageRequest);
	
	// 판매자별 상품 조회
//	Page<Seller> findAllById(String id, PageRequest pageRequest);
	
	// 등록대기 판매자, 등록완료 판매자 조회
//	List<Seller> findSellerByRoleNot(Role role);
	
	// 관리자 조회
//	List<Seller> findSellerByRole(Role role);
	
	// Seller role에 따라 조회
	Page<Seller> findSellerByRole(Role role, Pageable pageable);
	
	// 관리자 빼고 조회에 사용
	Page<Seller> findSellerByRoleNot(Role role, Pageable pageable);
}
