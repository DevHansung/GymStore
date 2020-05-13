package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.ProductDao;
import com.hansung.web.model.Board;
import com.hansung.web.model.Product;

//아래 어노테이션때문에 빈으로 등록된다.
@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Product getProductById(int productid) {
		return productDao.getProductById(productid);
	}

	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}
	
	public List<Product> getProductCategory(String category) {
		return productDao.getProductCategory(category);
	}
	
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}
	
	public List<Product> getAllProductPaging(int displayPost, int postNum){
	 return productDao.getAllProductPaging(displayPost, postNum);
	}
	
	public int getProductCount(){
	 return productDao.getProductCount();
	}
	
	public List<Product> getProductCategoryPaging(int displayPost, int postNum, String category){
	 return productDao.getProductCategoryPaging(displayPost, postNum, category);
	}
	
	public int getProductCategotyCount(String category){
	 return productDao.getProductCategotyCount(category);
	}
}
