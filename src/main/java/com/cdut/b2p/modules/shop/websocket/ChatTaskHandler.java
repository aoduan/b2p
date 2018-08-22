package com.cdut.b2p.modules.shop.websocket;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.JsonUtils;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.service.ShopChatService;
import com.cdut.b2p.modules.shop.websocket.po.Message;

@Component
public class ChatTaskHandler{
	private static Logger logger = LoggerFactory.getLogger(ChatTaskHandler.class);
	
	private static ConcurrentLinkedQueue<Message> concurrentLinkedQueue = new ConcurrentLinkedQueue<Message>(); 
	private static ConcurrentLinkedQueue<String> concurrentLinkedQueue_update = new ConcurrentLinkedQueue<String>(); 

	@Autowired
	private ShopChatService shopChatService;
	
	
	public static void andMessage(Message e) {
		concurrentLinkedQueue.add(e);
	}
	
	public static void andUpdate(String e) {
		concurrentLinkedQueue_update.add(e);
	}
	
	@Transactional(readOnly=false)
	public void handle() {
		for(Message e : concurrentLinkedQueue) {
			ShopChat chat = new ShopChat();
			chat.setChatFromId(e.getFrom_id());
			chat.setChatToId(e.getTo_id());
			chat.setChatMessage(e.getMsg());
			chat.setChatType(e.getType());
			chat.setCreateDate(e.getDate());
			chat.setUpdateDate(e.getDate());
			chat.setChatStatus(e.getStatus());
			chat.setId(e.getId());
			shopChatService.saveChat(chat);
			concurrentLinkedQueue.remove(e);
		}
		for(String s : concurrentLinkedQueue_update) {
			boolean rs = shopChatService.updateChatStatus(s);
			if(rs) {
				concurrentLinkedQueue_update.remove(s);
			}
		}
	}
}
