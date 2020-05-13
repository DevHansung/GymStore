package com.hansung.web.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
	private String orderid; // 주문번호

	@NotEmpty(message = "The Product name must not be null")
	private String username; // 주문자

	@NotEmpty(message = "The Product name must not be null")
	private String recipient; // 수취인

	@NotEmpty(message = "The Product name must not be null")
	private String address; // 주소

	@NotEmpty(message = "The Product name must not be null")
	private String phonenumber; // 핸드폰번호

	private int orderprice; // 주문 가격

	private String payment; // 결제정보

	private String orderdate; // 주문일시

	private String delivery; // 주문상태(배송)
}
