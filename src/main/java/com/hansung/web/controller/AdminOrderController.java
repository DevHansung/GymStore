package com.hansung.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hansung.web.model.Board;
import com.hansung.web.model.Order;
import com.hansung.web.model.OrderList;
import com.hansung.web.service.AdminOrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	@Autowired
	private AdminOrderService adminOrderService;

	@RequestMapping(value = "/adminOrderList", method = RequestMethod.GET)
	public String getOrderList(Model model, @RequestParam("page") int page) throws Exception {

		int count = adminOrderService.AdminOrderCount();
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

		List<Board> adminOrderList = null;
		adminOrderList = adminOrderService.AdminOrderListPaging(displayPost, postNum);

		model.addAttribute("adminOrderList", adminOrderList);
		model.addAttribute("pageNum", pageNum);

		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);

		return "adminOrderList";
	}

	@RequestMapping(value = "/adminOrderListDetail/{orderId}", method = RequestMethod.GET)
	public String getOrderListDetail(@PathVariable String orderId, Order order, OrderList orderList, Model model)
			throws Exception {

		order.setOrderid(orderId);
		List<OrderList> adminOrderListDetail = adminOrderService.AdminOrderView(order);

		model.addAttribute("adminOrderListDetail", adminOrderListDetail);

		return "adminOrderListDetail";
	}

	@RequestMapping(value = "/adminOrderUpdateDelivery", method = RequestMethod.POST)
	public String updateDelivery(Order order, @RequestParam("page") int page, RedirectAttributes redirectAttributes)
			throws Exception {

		adminOrderService.AdminUpdateDelivery(order);
		redirectAttributes.addAttribute("page", page);

		return "redirect:/admin/adminOrderList";
	}
}
