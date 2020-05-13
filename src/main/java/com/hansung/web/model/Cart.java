package com.hansung.web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Cart {

	private int id; // 장바구니 번호
	private String userid; // 사용자 아이디
	private int productid; // 상품 번호
	private String productname; // 상품 이름
	private int productprice; // 상품 단가
	private int amount; // 구매 수량
	private int totalprice; // 상품 가격 
}
