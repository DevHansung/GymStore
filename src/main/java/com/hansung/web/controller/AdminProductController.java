package com.hansung.web.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hansung.web.model.Product;
import com.hansung.web.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping
	public String admingPage() {
		return "admin";
	}

	@RequestMapping(value = "/adminProductInventory", method = RequestMethod.GET)
	public String getAdminProducts(Model model, @RequestParam("page") int page) {


		int count = productService.getProductCount();
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

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);
		model.addAttribute("count", count);

		List<Product> ProductList = null;
		ProductList = productService.getAllProductPaging(displayPost, postNum);
		model.addAttribute("ProductList", ProductList);

		return "adminProductInventory";
	}

	@RequestMapping("/adminProductCategory")
	public String getAdminProductCategory(@RequestParam("page") int page, @RequestParam("category") String category,
			Model model) {

		int count = productService.getProductCategotyCount(category);
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

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);
		model.addAttribute("category", category);
		model.addAttribute("count", count);

		List<Product> productCategory = null;
		productCategory = productService.getProductCategoryPaging(displayPost, postNum, category);
		model.addAttribute("productCategory", productCategory);

		return "adminProductCategory";

	}

	@RequestMapping(value = "/adminProductInventory/adminAddProduct", method = RequestMethod.GET)
	public String addProduct(@RequestParam("page") int page, Model model) {

		Product product = new Product();
		product.setCategory("컴퓨터");
		model.addAttribute("product", product);
		model.addAttribute("page", page);

		return "adminAddProduct";
	}

	@RequestMapping(value = "/adminProductInventory/adminAddProduct", method = RequestMethod.POST)
	public String addProductPost(@RequestParam("page") int page, @Valid Product product, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		// valid에 의해 검증된 후 결과가 bindingResult로 넘어오게 된다.
		if (result.hasErrors()) {
			System.out.println("form data has some errors ");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // Product.java에 선언해놓은 message들이 출력될것
			}
			return "adminAddProduct";
		}

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		String saveFileName = System.currentTimeMillis() +"-"+ productImage.getOriginalFilename();
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + saveFileName);

		if (productImage.isEmpty() == false) {
			System.out.println("--------file start--------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("--------file end--------");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		product.setImageFilename(saveFileName);

		productService.addProduct(product);
		redirectAttributes.addAttribute("page", page);

		return "redirect:/admin/adminProductInventory";
	}

	@RequestMapping(value = "/adminProductInventory/adminDeleteProduct", method = RequestMethod.GET)
	public String deleteProducts(@RequestParam("page") int page, @RequestParam("productid") int productid,
			HttpServletRequest request, RedirectAttributes redirectAttributes) { 

		Product product = productService.getProductById(productid);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());

		if (Files.exists(savePath)) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		productService.deleteProduct(product);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/admin/adminProductInventory";
	}

	@RequestMapping(value = "/adminProductInventory/adminUpdateProduct", method = RequestMethod.GET)
	public String updateProducts(@RequestParam("page") int page, @RequestParam("productid") int productid,
			Model model) {

		Product product = productService.getProductById(productid);

		model.addAttribute("product", product);
		model.addAttribute("page", page);
		return "adminUpdateProduct";

	}

	@RequestMapping(value = "/adminProductInventory/adminUpdateProduct", method = RequestMethod.POST)
	public String updateProductPost(@RequestParam("page") int page, @Valid Product product, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("form data has some errors ");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // Product.java에 선언해놓은 message들이 출력될것
			}
			return "adminUpdateProduct";
		}

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		product.setImageFilename(productImage.getOriginalFilename());

		productService.updateProduct(product);
		redirectAttributes.addAttribute("page", page);

		return "redirect:/admin/adminProductInventory";
	}

}
