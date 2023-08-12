package com.box.real_time_chatting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.box.real_time_chatting.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Integer>{

	UserModel findByEmail(String email);

	UserModel findByEmailAndPassword(String email, String password);
}
