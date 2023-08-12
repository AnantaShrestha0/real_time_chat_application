package com.box.real_time_chatting.controller;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.box.real_time_chatting.model.MessageModel;
import com.box.real_time_chatting.model.UserModel;
import com.box.real_time_chatting.repo.UserRepo;
import com.box.real_time_chatting.service.MessageService;
import com.box.real_time_chatting.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	
	
	@GetMapping("/chat/{userId}")
    public String userDetails(@PathVariable int userId, Model model,HttpSession httpSession) {
        
		UserModel userModel=(UserModel) httpSession.getAttribute("validuser");
		if(userModel==null) {
			return "redirect:/signin";
		}
		
//		int userId=Integer.parseInt(id);
		
		UserModel person=userService.findById(userId);
		model.addAttribute("person", person);
		model.addAttribute("user", userModel);
		return "chat";

    }
	
	@PostMapping("/sendMessage")
	public String postMessage(MessageModel messageModel) {
		messageService.addMessage(messageModel);
		return "redirect:/chat/"+messageModel.getOutgoingMsgId();
	}
}
