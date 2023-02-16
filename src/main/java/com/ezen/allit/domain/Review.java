package com.ezen.allit.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"product", "seller", "member"})
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rvno;								 // 리뷰 일련번호
	private int prvno;								 // 부모리뷰 일련번호
	private String content;							 // 내용
	private int rating;							 	 // 별점
	private int hit;							 	 // 좋아요
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pno")
	private Product product;						 // 상품 정보
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid")
	private Seller seller;							 // 판매자 정보
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mid")
	private Member member;							 // 작성자 정보
	@OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"review"})
	private List<Hit> hits = new ArrayList<>();	  	 // 연관관계 설정용
	@OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"review"})
	private List<ReviewFile> reviewFile = new ArrayList<>(); // 다중 리뷰이미지
	@OneToOne
	@JoinColumn(name = "odno")
	private OrdersDetail ordersDetail; 		  		 // 주문상세 정보
	@CreationTimestamp
	private Date regDate;							 // 작성일
	private Date modDate;							 // 수정일
}
