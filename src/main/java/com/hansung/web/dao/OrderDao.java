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
public class OrderDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertOrder(Order order) {
		sqlSession.insert("OrderMapper.insertOrder", order);
	}

	public void insertOrderDetail(OrderDetail orderDetail) {
		sqlSession.insert("OrderMapper.insertOrderDetail", orderDetail);
	}

	public List<Order> listOrder(Order order){
		return sqlSession.selectList("OrderMapper.orderList", order);
	}
	
	public List<OrderList> listOrderView(Order order){
		return sqlSession.selectList("OrderMapper.orderListView", order);
	}
	
	public List<Board> orderListPaging(String username, int displayPost, int postNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("displayPost", displayPost);
		map.put("postNum", postNum);
		return sqlSession.selectList("OrderMapper.orderListPaging", map);
	}
	
	public int orderCount(String username){
	 return sqlSession.selectOne("OrderMapper.orderCount", username); 
	}
}
