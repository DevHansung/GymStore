package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.AdminOrderDao;
import com.hansung.web.model.Board;
import com.hansung.web.model.Order;
import com.hansung.web.model.OrderList;

@Service
public class AdminOrderService {

	@Autowired
	private AdminOrderDao adminOrderDao;

	public List<Order> AdminOrderList(){
		return adminOrderDao.AdminOrderList();
	}
	
	public List<OrderList> AdminOrderView(Order order){
		return adminOrderDao.AdminOrderView(order);
	}
	public void AdminUpdateDelivery(Order order) {
		adminOrderDao.AdminUpdateDelivery(order);
	}
	
	public List<Board> AdminOrderListPaging(int displayPost, int postNum) {
		return adminOrderDao.AdminOrderListPaging(displayPost, postNum);
	}

	public int AdminOrderCount() {
		return adminOrderDao.AdminOrderCount();
	}
}
