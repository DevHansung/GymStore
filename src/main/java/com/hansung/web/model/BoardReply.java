package com.hansung.web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BoardReply {

	private int replyNum; // 장바구니 번호
	private int boardNum; // 사용자 아이디
	private String replyText; // 상품 번호
	private String replyWriter; // 상품 이름
	private String replyDate; // 상품 단가

}
