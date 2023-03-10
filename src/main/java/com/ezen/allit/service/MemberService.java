package com.ezen.allit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ezen.allit.domain.Hit;
import com.ezen.allit.domain.Member;
import com.ezen.allit.domain.QnA;
import com.ezen.allit.domain.Review;

import com.ezen.allit.dto.HitDto;
import com.ezen.allit.dto.MemberDto;
import com.ezen.allit.dto.ReviewDto;

public interface MemberService {
	
	/* <== 회원 정보 ==> */
	public Member getMember(Member member);
	
	public void saveMember(Member member);
	
	Member modifyMember(Member member);
	
	Member modifySnsMemberInfo(MemberDto memberDto);
	
	boolean checkPwd(MemberDto memberDto);
	
	boolean checkPwd2(Member member);
	
	Member modifyMemberPwd(MemberDto memberDto);

	void modifyPwd(MemberDto memberDto);
	
	public int idCheck(String id);
	
	public Member findById(Member member); 
	
	public Member findByPw(Member member);
	
	public void deleteMember(String id);
	
	/* <== 상품 주문 ==> */
	/** 상품 구매시 올잇머니 차감 */
	void minusMoney(String id, int amount);
	
	/** 취소/반품시 올잇머니 환불 */
	void addMoney(String id, int amount);
	
	/** 상품 구매시 포인트 사용 */
	void minusPoint(String id, int amount);
	
	/** 구매확정 후 포인트 적립 */
	void addPoint(String id, int amount);

	/* <== 1:1문의(QnA) ==> */
	public void saveQna(QnA qna);
	
	public Page<QnA> getQnaList(Member member, Pageable pageable);
	
	public QnA getQnaDetail(int qno);
	
	void hitProduct(HitDto hitSaveRequestDto); // 상품 좋아요
	
	Page<Review> getReviewList(String id, Pageable pageable); // 리뷰목록 조회

	void saveReview(ReviewDto reviewDto) throws Exception; // 리뷰작성
	
	Page<Hit> getLikeList(String id, Pageable pageable); // 좋아요목록 조회
	
	void deleteHit(HitDto hitDto); // 좋아요 취소
	}
