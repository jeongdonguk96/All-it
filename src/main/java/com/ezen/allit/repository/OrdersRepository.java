package com.ezen.allit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ezen.allit.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	@Modifying
	@Query(value =  "INSERT INTO orders(mid, final_price, use_point, reg_date) VALUES(?1, ?2, ?3, sysdate)", nativeQuery = true)
	int saveOrderSequence(String mid, int finalPrice, int usePoint);

	// 사용자 주문목록조회
	Page<Orders> findAllByMemberId(String id, Pageable pageable);

}
