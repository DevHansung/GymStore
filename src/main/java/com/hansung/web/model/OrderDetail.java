package com.hansung.web.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetail {

	private int orderdetailid;
	private String orderid;
	private String imageFilename;
	private int productid;
	private int amount;
	private String username; //주문상세페이지에 각 사용자 장바구니별로 잘 들어가도록 검증하기위함.
}
