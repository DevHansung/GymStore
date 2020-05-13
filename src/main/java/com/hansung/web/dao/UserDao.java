package com.hansung.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hansung.web.model.Board;
import com.hansung.web.model.User;

@Repository
public class UserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		sqlSession.insert("UserMapper.insertUser", user);
	}

	public User getUserById(int id) {
		return sqlSession.selectOne("UserMapper.getUser", id);
	}

	public User getUserByUsername(String username) {
		return sqlSession.selectOne("UserMapper.getUserName", username);
	}

	public List<User> getAllUsers() {
		return sqlSession.selectList("UserMapper.getAllUser", null);
	}

	public void updateAuthority(User user) {
		sqlSession.update("UserMapper.updateAuthority", user);
	}

	public void deleteUser(User user) {
		sqlSession.delete("UserMapper.deleteUser", user);
	}
}
