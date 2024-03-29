package com.box.real_time_chatting.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.box.real_time_chatting.model.MessageModel;

public interface MessageRepo extends JpaRepository<MessageModel, Integer> {

	List<MessageModel> findByIncomeMsgIdAndOutgoingMsgId(int incomingmsgid, int outgoingmsgid);

	
	
}
