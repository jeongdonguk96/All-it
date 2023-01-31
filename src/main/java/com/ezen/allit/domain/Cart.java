package com.ezen.allit.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@ToString(exclude = {"member"})
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int cno;		 // 장바구니 일련번호
	private int quantity;	 // 수량
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mid")
	private Member member;	 // member 정보
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pno")
	private Product product; // product 정보
}