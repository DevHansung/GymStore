package com.hansung.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hansung.web.model.Product;
import com.hansung.web.service.ProductService;

@Controller
public class ProductController { // controller -> service -> dao

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getListPage(Model model, @RequestParam("page") int page) {

		int count = productService.getProductCount();
		int postNum = 12;
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
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);

		List<Product> ProductList = null;
		ProductList = productService.getAllProductPaging(displayPost, postNum);
		model.addAttribute("ProductList", ProductList);
		
		return "products";
	}

	@RequestMapping("/productCategory")
	public String getProductCategory(@RequestParam("page") int page, @RequestParam("category") String category,
			Model model) {
		int count = productService.getProductCategotyCount(category);
		int postNum = 12;
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
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);
		model.addAttribute("category", category);

		List<Product> productCategory = null;
		productCategory = productService.getProductCategoryPaging(displayPost, postNum, category);

		model.addAttribute("productCategory", productCategory);
	
		return "productCategory";

	}

	@RequestMapping("/viewProduct")
	public String viewProduct(@RequestParam("page") int page, @RequestParam("productid") int productid,
			@RequestParam("category") String category, Model model, RedirectAttributes redirectAttributes) {

		Product product = productService.getProductById(productid);

		model.addAttribute("product", product);
		model.addAttribute("page", page);
		model.addAttribute("category", category);

		return "viewProduct";
	}

}
