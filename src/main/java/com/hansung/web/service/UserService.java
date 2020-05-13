package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.UserDao;
import com.hansung.web.model.Board;
import com.hansung.web.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void updateAuthority(User user) {
		userDao.updateAuthority(user);
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}
	
}
