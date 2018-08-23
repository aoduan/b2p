package com.cdut.b2p.common.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.WebSocketSession;

import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.service.ShopChatService;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.shop.websocket.po.Message;
import com.cdut.b2p.modules.shop.websocket.task.ChatTaskHandler;

@Component
public class ShopGoodsTaskHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ShopGoodsTaskHandler.class);
	private static final Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	@Autowired
	private ShopGoodsService shopGoodsService;
	
	public static void andUpdate(String e) {
		Integer i = map.get(e);
		if(i == null) {
			map.put(e, 1);
		}else {
			map.replace(e, i + 1);
		}
	}
	
	@Transactional(readOnly=false)
	public void handle() {
		for(Map.Entry<String, Integer> entry : map.entrySet()) { 
			shopGoodsService.updateGoodsClickTimes(entry.getKey(), entry.getValue());
			map.remove(entry.getKey());
		}
	}
}

