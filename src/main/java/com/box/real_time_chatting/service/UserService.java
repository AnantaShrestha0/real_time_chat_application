package com.box.real_time_chatting.service;



import java.util.List;

import com.box.real_time_chatting.model.UserModel;

public interface UserService {

   void addUser(UserModel userModel);
   UserModel sameUser(String email);
   UserModel finduser(String email,String password);
   List<UserModel> userlist();
   UserModel findById(int id);
   
   
}
