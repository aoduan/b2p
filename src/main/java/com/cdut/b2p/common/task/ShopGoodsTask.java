package com.cdut.b2p.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cdut.b2p.modules.shop.websocket.task.ChatTask;
import com.cdut.b2p.modules.shop.websocket.task.ChatTaskHandler;

@Component
public class ShopGoodsTask {
	
	private static Logger logger = LoggerFactory.getLogger(ShopGoodsTask.class);
	
	@Autowired
	ShopGoodsTaskHandler shopGoodsTaskHandler;
	
	@Scheduled(cron = "0 0 2 * * ?")
	//@Scheduled(cron = "*/5 * * * * ?")
	public void execute() {
		logger.debug("execute  ShopGoodsTask");
		try {
			shopGoodsTaskHandler.handle();
			
		} catch (Exception e) {
			logger.error("ShopGoodsTask", e);
		}
	}
}
