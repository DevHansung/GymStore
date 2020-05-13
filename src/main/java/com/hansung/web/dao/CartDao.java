package com.hansung.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hansung.web.model.Cart;

@Repository
public class CartDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertCart(Cart cart) {
		sqlSession.insert("Cart.insertCart", cart);
	}

	public List<Cart> listCart(String userId) {
		return sqlSession.selectList("Cart.listCart", userId);
	}

	public void deleteCart(int cartId) {
		sqlSession.delete("Cart.deleteCart", cartId);
	}

	public void deleteAllCart(String userId) {
		sqlSession.delete("Cart.deleteAllCart", userId);
	}

	public void modifyCart(Cart cart) {
		sqlSession.update("Cart.modifyCart", cart);
	}

	public int sumMoney(String userId) {
		return sqlSession.selectOne("Cart.sumMoney", userId);
	}

	public int countCart(int productid, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productid", productid);
		map.put("userid", userId);
		System.out.println(map);
		return sqlSession.selectOne("Cart.countCart", map);
	}

	public void updateCart(Cart cart) {
		sqlSession.update("Cart.updateCart", cart);
	}
}
