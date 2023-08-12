package com.box.real_time_chatting.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.box.real_time_chatting.model.UserModel;
import com.box.real_time_chatting.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public String postSignUp(UserModel userModel,@RequestParam("img") MultipartFile imagefile,Model model) {
		String email=userModel.getEmail();
		UserModel user =userService.sameUser(email);
		if(user==null) {
			String unqiue;
			
			
			if(!imagefile.isEmpty()) {
				try {
					 unqiue=generateUniqueFileName(imagefile.getOriginalFilename());
					Files.copy(imagefile.getInputStream(), Path.of("src/main/resources/static/images/"+unqiue), StandardCopyOption.REPLACE_EXISTING);
				    Random random=new Random();
				    int randomid=random.nextInt(100000000);
			        userModel.setUserid(randomid);
					userModel.setImage(unqiue);
					userService.addUser(userModel);
		            
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			return "redirect:/signin";
		}
		model.addAttribute("same_user_name", "This email is already registered");
		return "index";
	}
	
	 private String generateUniqueFileName(String originalFileName) {
	        String extension = StringUtils.getFilenameExtension(originalFileName);
	        return UUID.randomUUID().toString() + "." + extension;
	    }
	 
	 @PostMapping("/signin")
	 public String postSignin(UserModel userModel,Model model,HttpSession httpSession,RedirectAttributes redirectAttributes) {
		 String email=userModel.getEmail();
		 String password=userModel.getPassword();
		 UserModel user=userService.finduser(email, password);
		 if(user==null) {
			 model.addAttribute("error","Email and password not matched");
			 return "signin";
		 }
		 httpSession.setAttribute("validuser",user);
			//session.setMaxInactiveInterval(9999999*99999*999999);
//		 List<UserModel> userList=userService.userlist();
//		 userList.remove(user);
//		 redirectAttributes.addAttribute("userlist", userList);
//		 redirectAttributes.addAttribute("userobj", user.getImage());
		 return "redirect:/homepage";
//		 model.addAttribute("userlist",userList);
//		 model.addAttribute("userobj",user);
//		
//		 return "homepage";
	 }
	 
	 @GetMapping("logout")
	 public String getLogOut(HttpSession httpSession) {
		 httpSession.invalidate();
		 return "redirect:/signin";
		 }
	
	 
	 
}
