package com.ezen.allit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ezen.allit.domain.Grade;
import com.ezen.allit.domain.Member;
import com.ezen.allit.domain.QnA;
import com.ezen.allit.domain.Role;
import com.ezen.allit.domain.Seller;
import com.ezen.allit.repository.MemberRepository;
import com.ezen.allit.repository.QnARepository;
import com.ezen.allit.repository.SellerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DataInsertTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private SellerRepository sellerRepo;
	
	@Autowired
	private QnARepository qnaRepo;
	
	@Test
	@Disabled
	public void testDataInsert() {
		
		for(int i=1; i<11; i++) {
			Member member = new Member();
			
			member.setId("aaaa"+i);
			member.setPwd("1234");
			member.setName("aaaa"+i);
			member.setGrade(Grade.BRONZE);
			member.setEmail("aaaa"+i+"@google.com");
			member.setBirth("2015/11/"+i);
			member.setPhone("010-1111-1111");
			member.setAddress("서울 관악구");
			member.setZipcode("111-111");
			
			memberRepo.save(member);
		}
		
		Seller seller = new Seller();
		
		seller.setId("ad1234");
		seller.setPwd("aaaa");
		seller.setName("관리자");
		seller.setContent("관리자");
		seller.setEmail("ad1234.google.com");
		seller.setPhone("010-1234-1234");
		seller.setRole(Role.ADMIN);
		seller.setAddress("서울 관악구");
		seller.setZipcode("111-1122");
		
		sellerRepo.save(seller);
	}
	
	@Test
	@Disabled
	public void testDataInsert1() {
		
		for(int i=1; i<11; i++) {
			QnA qna = new QnA();
			Member member = new Member();
			
			if(i%3 == 1) {
				qna.setCategory("결제");
			}else if(i%3 == 2) {
				qna.setCategory("상품문의");
			}else {
				qna.setCategory("기타");
			}
			qna.setContent("QnA 내용 "+i);
			if(i<9) {
				member.setId("aaaa"+i);
				qna.setMember(member);
			}else {
				member.setId("aaaa1");
				qna.setMember(member);
			}
			qna.setQno(i);
			qna.setStatus("0");
			
			qnaRepo.save(qna);
		}		
	}
	
	@Test
	@Disabled
	public void testDataInsert2() {
		
		for(int i=1; i<11; i++) {
			Seller seller = new Seller();
			
			seller.setId("판매자"+i);
			seller.setPwd("aaaa");
			seller.setName("판매자"+i);
			seller.setContent("물건판다");
			seller.setEmail("www.google"+i+".com");
			seller.setPhone("010-1122-3344");
			seller.setAddress("서울시 관악구");
			seller.setZipcode("123-456");
			int a = i+8;
			seller.setRegno("1111-33-22-"+a);
			if(i<7)
				seller.setRole(Role.SELLER);
			else
				seller.setRole(Role.TEMP);
			
			sellerRepo.save(seller);
		}		
	}
}