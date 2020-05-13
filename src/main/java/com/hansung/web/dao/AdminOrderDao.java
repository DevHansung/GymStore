package com.hansung.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hansung.web.model.Board;
import com.hansung.web.model.Order;
import com.hansung.web.model.OrderDetail;
import com.hansung.web.model.OrderList;

@Repository
public class AdminOrderDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Order> AdminOrderList(){
		return sqlSession.selectList("AdminOrderMapper.adminOrderList");
	}
	 
	public List<OrderList> AdminOrderView(Order order){
		return sqlSession.selectList("AdminOrderMapper.adminOrderView", order);
	}
	public void AdminUpdateDelivery(Order order){
		sqlSession.insert("AdminOrderMapper.adminUpdateDelivery", order);
	}
	
	public List<Board> AdminOrderListPaging(int displayPost, int postNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("displayPost", displayPost);
		map.put("postNum", postNum);
		return sqlSession.selectList("AdminOrderMapper.adminOrderListPaging", map);
	}
	
	public int AdminOrderCount(){
	 return sqlSession.selectOne("AdminOrderMapper.adminOrderCount"); 
	}
	
}
