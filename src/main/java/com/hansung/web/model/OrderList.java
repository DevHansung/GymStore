package com.hansung.web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderList {

	private String orderid;
	private String username; //주문자
	private String recipient; //수령인
	private String address; //주소
	private String phonenumber; //휴대폰번호
	private int orderprice; //주문가격
	private String payment; //결제수단
	private String orderdate; //주문일시
	
	private int orderdetailid; //장바구니 상세 번호
	private int productid; //상품번호
	private int amount; //수량
	
	private String name; //상품명
	private String imageFilename; //상품 이미지
	private int price; //상품개별가격
	
}
