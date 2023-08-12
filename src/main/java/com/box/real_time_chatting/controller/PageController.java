package com.box.real_time_chatting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.box.real_time_chatting.model.UserModel;
import com.box.real_time_chatting.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {


	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/signin")
	public String getSignIn() {
		return "signin";
	}
	
	 @GetMapping("/homepage")
	 public String getHomepage(HttpSession httpSession,Model model) {
		 if(httpSession.getAttribute("validuser")==null) {
			 return "redirect:/signin";
		 }
		 UserModel user=(UserModel) httpSession.getAttribute("validuser");
		 List<UserModel> userList=userService.userlist();
		 userList.remove(user);
		 model.addAttribute("userlist",userList);
		 model.addAttribute("userobj",user);
		 return "homepage";
	 }
}
