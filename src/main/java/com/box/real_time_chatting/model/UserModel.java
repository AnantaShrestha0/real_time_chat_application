package com.box.real_time_chatting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserModel {

	@Id
	@GeneratedValue
	private int id;
	private int userid;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String image;
	private String active;
	
}
