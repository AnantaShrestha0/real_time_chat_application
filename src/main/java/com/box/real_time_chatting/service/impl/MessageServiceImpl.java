package com.box.real_time_chatting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.box.real_time_chatting.model.MessageModel;
import com.box.real_time_chatting.repo.MessageRepo;
import com.box.real_time_chatting.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepo messageRepo;
	
	@Override
	public void addMessage(MessageModel messageModel) {
		// TODO Auto-generated method stub
		messageRepo.save(messageModel);
	}

}
