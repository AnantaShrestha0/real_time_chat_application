package com.box.real_time_chatting.service;

import java.util.List;

import com.box.real_time_chatting.model.MessageModel;

public interface MessageService {

	void addMessage(MessageModel messageModel);
	List<MessageModel> allchat(int incommingMsgId,int outgoingMsgId);
}
