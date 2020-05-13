package com.hansung.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hansung.web.model.Cart;
import com.hansung.web.model.User;
import com.hansung.web.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	//회원가입 폼
	@RequestMapping("/register")
	public String registerUser(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "registerUser";
	}

	//회원가입
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserPost(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registerUser"; // user, errormessage가 담겨서 넘어가기 때문에 입력했던거 그대로 보임
		}

		// 유저중복 검사
		List<User> userList = userService.getAllUsers();

		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equals(userList.get(i).getUsername())) {
				model.addAttribute("usernameMsg", "username already exist");
				return "registerUser";
			}
		}
		user.setEnabled(true);

		if (user.getUsername().equals("admin"))
			user.setAuthority("ROLE_ADMIN"); //최초 설정한 id가 admin일 경우 관리자권한 부여
		else
			user.setAuthority("ROLE_USER"); //admin이 아닐경우 일반 유저
		userService.addUser(user);

		return "registerUserSuccess";

	}

}
