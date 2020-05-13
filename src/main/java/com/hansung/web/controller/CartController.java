package com.hansung.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hansung.web.dao.OrderDao;
import com.hansung.web.model.Cart;
import com.hansung.web.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	// 장바구니 추가
	@RequestMapping(value = "/insertCart")
	public String insert(@ModelAttribute Cart cart, @RequestParam("productprice") int productprice,
			@RequestParam("amount") int amount, Principal principal) {

		String userId = principal.getName();
		int totalprice = productprice * amount;

		cart.setUserid(userId);
		cart.setTotalprice(totalprice);

		// 장바구니에 기존 상품이 있는지 검사
		int count = cartService.countCart(cart.getProductid(), cart.getUserid());
		if (count == 0) {
			// 없으면 insert
			cartService.insertCart(cart);
		} else {
			// 있으면 update
			cartService.updateCart(cart);
		}
		return "redirect:/cartList";
	}

	// 장바구니 목록
	@RequestMapping("/cartList")
	public String list(Model model, Principal principal) {

		String userId = principal.getName();

		int sumMoney = cartService.sumMoney(userId); // 장바구니 전체 금액 호출

		int fee = sumMoney >= 30000 ? 0 : 2500;
		int allSum = sumMoney + fee;
		List<Cart> cart = cartService.listCart(userId);
		int list = cart.size();
		model.addAttribute("cart", cart);
		model.addAttribute("fee", fee);
		model.addAttribute("sumMoney", sumMoney);
		model.addAttribute("allSum", allSum);
		model.addAttribute("list", list);

		return "cartList";
	}

	// 장바구니 삭제
	@RequestMapping("/cartList/deleteCart/{id}")
	public String delete(@PathVariable int id) {
		cartService.deleteCart(id);
		return "redirect:/cartList";
	}

	// 장바구니 수정
	@RequestMapping("/cartList/updateCart")
	public String update(@RequestParam("amount") int[] amount, @RequestParam("productid") int[] productid,
			Principal principal) {
		String userid = principal.getName();
		for (int i = 0; i < productid.length; i++) {
			Cart cart = new Cart();
			cart.setUserid(userid);
			cart.setAmount(amount[i]);
			cart.setProductid(productid[i]);
			cartService.modifyCart(cart);
		}
		return "redirect:/cartList";
	}
}
