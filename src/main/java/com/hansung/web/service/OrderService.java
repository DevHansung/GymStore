package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.OrderDao;
import com.hansung.web.model.Board;
import com.hansung.web.model.Order;
import com.hansung.web.model.OrderDetail;
import com.hansung.web.model.OrderList;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public void insertOrder(Order order) {
		orderDao.insertOrder(order);
	}
	public void insertOrderDetail(OrderDetail orderdetail) {
		orderDao.insertOrderDetail(orderdetail);
	}
	public List<Order> listOrder(Order order){
		return orderDao.listOrder(order);
	}
	public List<OrderList> listOrderView(Order order){
		return orderDao.listOrderView(order);
	}
	public List<Board> orderListPaging(String username, int displayPost, int postNum) {
		return orderDao.orderListPaging(username, displayPost, postNum);
	}
	public int orderCount(String username) {
		return orderDao.orderCount(username);
	}
}
