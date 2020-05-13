package com.hansung.web.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.CartDao;
import com.hansung.web.model.Cart;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public void insertCart(Cart cart) {
        cartDao.insertCart(cart);
    }
    public List<Cart> listCart(String userid) {
        return cartDao.listCart(userid);
    }
    public void deleteCart(int id) {
        cartDao.deleteCart(id);
    }
    public void modifyCart(Cart cart) {
        cartDao.modifyCart(cart);
    }
    public int sumMoney(String userid) {
        return cartDao.sumMoney(userid);
    }
    public int countCart(int productId, String userId) {
        return cartDao.countCart(productId, userId);
    }
    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }
    public void deleteAllCart(String userId) {
        cartDao.deleteAllCart(userId);
    }
    
}