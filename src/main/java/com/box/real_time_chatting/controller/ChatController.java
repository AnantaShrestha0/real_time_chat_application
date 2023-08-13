package com.box.real_time_chatting.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<MessageModel> chatListone=messageService.allchat(person.getUserid(), userModel.getUserid());
		List<MessageModel> chatListtwo=messageService.allchat(userModel.getUserid(),person.getUserid());
		List<MessageModel> chatlist=new ArrayList<>();
		chatlist.addAll(chatListone);
		chatlist.addAll(chatListtwo);
		
		Comparator<MessageModel> idComparator=Comparator.comparingInt(MessageModel::getId);
		Collections.sort(chatlist, idComparator);
		model.addAttribute("chatlist", chatlist);
		return "chat";

    }
	
	@PostMapping("/sendMessage")
	public String postMessage(MessageModel messageModel,@RequestParam int personid,HttpSession httpSession) {
		
		UserModel userModel=(UserModel) httpSession.getAttribute("validuser");
		if(userModel==null) {
			return "redirect:/signin";
		}
		
		messageService.addMessage(messageModel);
		return "redirect:/chat/"+personid;
	}
}
