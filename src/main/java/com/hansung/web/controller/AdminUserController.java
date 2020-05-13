package com.hansung.web.controller;

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

import com.hansung.web.model.Board;
import com.hansung.web.model.Product;
import com.hansung.web.model.User;
import com.hansung.web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/adminUserList")
	public String userList(Model model) throws Exception {

		List<User> user = userService.getAllUsers(); 
		model.addAttribute("user", user); 

		return "adminUserList";

	}

	@RequestMapping(value = "/adminUserList/adminUserManagement/{userId}", method = RequestMethod.GET)
	public String updateUser(@PathVariable int userId, Model model) {

		User user = userService.getUserById(userId);

		model.addAttribute("user", user);

		return "adminUserManagement";
	}

	@RequestMapping(value = "/adminUserList/adminUserManagement", method = RequestMethod.POST)
	public String updateBoardPost(User user, BindingResult result, HttpServletRequest request) {
		userService.updateAuthority(user);

		return "redirect:/admin/adminUserList";
	}

	@RequestMapping(value = "/adminUserList/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int userId, HttpServletRequest request) { 

		User user = userService.getUserById(userId);
		userService.deleteUser(user);

		return "redirect:/admin/adminUserList";
	}
}