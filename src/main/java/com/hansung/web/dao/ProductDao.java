package com.hansung.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hansung.web.model.Board;
import com.hansung.web.model.Product;

@Repository
public class ProductDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Product getProductById(int productid) {
		return sqlSession.selectOne("ProductMapper.getProduct", productid);
	}

	public List<Product> getAllProduct() {
		return sqlSession.selectList("ProductMapper.getAllProduct", null);
	}

	public List<Product> getProductCategory(String category) {
		return sqlSession.selectList("ProductMapper.getProductCategory", category);
	}

	public void addProduct(Product product) {
		sqlSession.insert("ProductMapper.insertProduct", product);
	}

	public void deleteProduct(Product product) {
		sqlSession.delete("ProductMapper.deleteProduct", product);
	}

	public void updateProduct(Product product) {
		sqlSession.update("ProductMapper.updateProduct", product);
	}

	public List<Product> getAllProductPaging(int displayPost, int postNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("displayPost", displayPost);
		map.put("postNum", postNum);
		return sqlSession.selectList("ProductMapper.getAllProductPaging", map);
	}

	public int getProductCount() {
		return sqlSession.selectOne("ProductMapper.getProductCount");
	}

	public List<Product> getProductCategoryPaging(int displayPost, int postNum, String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("displayPost", displayPost);
		map.put("postNum", postNum);
		map.put("category", category);
		return sqlSession.selectList("ProductMapper.getProductCategoryPaging", map);
	}

	public int getProductCategotyCount(String category) {
		return sqlSession.selectOne("ProductMapper.getProductCategotyCount", category);
	}
}
