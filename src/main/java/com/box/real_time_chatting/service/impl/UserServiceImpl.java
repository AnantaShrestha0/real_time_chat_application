package com.box.real_time_chatting.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.box.real_time_chatting.model.UserModel;
import com.box.real_time_chatting.repo.UserRepo;
import com.box.real_time_chatting.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public void addUser(UserModel userModel) {
		// TODO Auto-generated method stub
		userRepo.save(userModel);
	}

	@Override
	public UserModel sameUser(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public UserModel finduser(String email, String password) {
		// TODO Auto-generated method stub
		
		return userRepo.findByEmailAndPassword(email,password);
	}

	@Override
	public List<UserModel> userlist() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public UserModel findById(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

	
	

	
	
	
}
