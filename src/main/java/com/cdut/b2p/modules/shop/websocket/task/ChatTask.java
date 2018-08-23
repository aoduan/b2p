package com.cdut.b2p.modules.shop.websocket.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChatTask {
	private static Logger logger = LoggerFactory.getLogger(ChatTask.class);
	
	@Autowired
	ChatTaskHandler chatTaskHandler;

	@Scheduled(cron = "*/5 * * * * ?")
	public void execute() {
		logger.debug("execute  ChatTask");
		try {
			chatTaskHandler.handle();
		} catch (Exception e) {
			logger.error("ChatTask", e);
		}
	}

}
