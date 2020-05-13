package com.hansung.web.controller;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.web.model.Board;
import com.hansung.web.model.Cart;
import com.hansung.web.model.Order;
import com.hansung.web.model.OrderDetail;
import com.hansung.web.model.OrderList;
import com.hansung.web.model.Product;
import com.hansung.web.service.CartService;
import com.hansung.web.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/orderForm")
	public String OrderForm(Model model, Principal principal) {

		String userId = principal.getName();

		int sumMoney = cartService.sumMoney(userId); // 장바구니 전체 금액 호출

		int fee = sumMoney >= 30000 ? 0 : 2500;
		int allSum = sumMoney + fee;

		List<Cart> cart = cartService.listCart(userId);
		System.out.println(cart);
		model.addAttribute("cart", cart);
		model.addAttribute("fee", fee);
		model.addAttribute("sumMoney", sumMoney);
		model.addAttribute("allSum", allSum);
		System.out.println(cart.getClass().getName());

		return "orderForm";
	}

	@RequestMapping(value = "/orderSuccess", method = RequestMethod.POST)
	public String OrderFormPost(Order order, OrderDetail orderDetail, Principal principal) {

		String userName = principal.getName(); // session의 id값을 가져와 userId에 저장

		// 날자와 난수를 조합하여 고유값을 가진 주문번호 생성
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		String subNum = "";

		for (int i = 1; i <= 6; i++) {
			subNum += (int) (Math.random() * 10);
		}

		String orderId = ymd + "_" + subNum; // 위의 로직으로 난수의 주문번호 생성

		order.setOrderid(orderId);
		order.setUsername(userName);
		orderService.insertOrder(order); 
		orderDetail.setOrderid(orderId);
		orderDetail.setUsername(userName);
		orderService.insertOrderDetail(orderDetail);
		cartService.deleteAllCart(userName);

		return "orderSuccess";
	}

	@RequestMapping("/orderList")
	public String GetOrderList(@RequestParam("page") int page, Order order, Model model, Principal principal) {

		String username = principal.getName();

		order.setUsername(username);

		int count = orderService.orderCount(username);
		int postNum = 10;
		int pageNum = (int) Math.ceil((double) count / postNum);
		int displayPost = (page - 1) * postNum;
		int pageNum_cnt = 10;
		int endPageNum = (int) (Math.ceil((double) page / (double) pageNum_cnt) * pageNum_cnt);
		int startPageNum = endPageNum - (pageNum_cnt - 1);
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		List<Board> orderList = null;
		orderList = orderService.orderListPaging(username, displayPost, postNum);

		model.addAttribute("orderList", orderList);
		model.addAttribute("pageNum", pageNum);

		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);

		return "orderList";
	}

	@RequestMapping(value = "/orderListDetail/{orderId}", method = RequestMethod.GET)
	public String GetOrderListDetail(@PathVariable String orderId, Order order, OrderList orderList, Model model,
			Principal principal) throws Exception {

		String userName = principal.getName();
		order.setUsername(userName);
		order.setOrderid(orderId);
		List<OrderList> orderListDetail = orderService.listOrderView(order);
		model.addAttribute("orderListDetail", orderListDetail);

		return "orderListDetail";
	}

}
