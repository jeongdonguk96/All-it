package com.ezen.allit.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.allit.domain.Hit;

public interface HitRepository extends JpaRepository<Hit, Integer> {
	// 상품 좋아요(중복확인)
	Optional<Hit> findByProductPnoAndMemberId(int pno, String mid);
	
	// 댓글 좋아요(중복확인)
	Optional<Hit> findByReviewRvnoAndMemberId(int rvno, String mid);
	
	// 좋아요한 상품목록 조회
	Page<Hit> findAllByMemberIdAndProductNotNull(String id, Pageable pageable);
}
