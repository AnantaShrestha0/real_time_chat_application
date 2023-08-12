package com.box.real_time_chatting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MessageModel {

	@Id
	@GeneratedValue
	private int id;
	private int incomeMsgId;
	private int outgoingMsgId;
	private String msg;
}
