package com.poscoict.mysite.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo uservo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		if (result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) {
//				System.err.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "user/login";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		Long userNo = authUser.getNo();
		UserVo userVo = userService.getUser(userNo);
		model.addAttribute("userVo", userVo);

		return "user/update";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {

		authUser.setName(userVo.getName());
		authUser.setPassword(userVo.getPassword());
		authUser.setGender(userVo.getGender());

		if (userService.updateUser(authUser)) {
			authUser.setPassword(null);
			authUser.setGender(null);
		}

		return "redirect:/user/update";
	}

	// 해당 Controller 전용 에러처리
//	@ExceptionHandler(Exception.class)
//	public String UserControllerExceptionHandler() {
//		return "error/exception";
//	}

}
