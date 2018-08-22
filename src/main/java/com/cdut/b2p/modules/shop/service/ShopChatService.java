package com.cdut.b2p.modules.shop.service;


import java.util.Date;
import java.util.List;



import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.websocket.po.Message;

/**
 * @title ShopChatService
 * @desc   用户商家交流接口
 * @author zsb
 *
 */

public interface ShopChatService {
	/**
	 * @desc  添加一条聊天记录
	 * @param shopChat
	 * @return boolean
	 */
	public boolean saveChat(ShopChat shopChat);
	/**
	 * @desc 根据id，查询相应的聊天记录
	 * @param id
	 * @return
	 */
	public ShopChat findChatById(String id);
	/**
	 * @desc 根据用户id，查询其所发表的交流
	 * @param id
	 * @return
	 */
	public List<ShopChat> findChatByUser(String uid);
	
	/**
	 * @desc 查询所有的交流记录
	 * @return
	 */
	public List<ShopChat> findAllChat();
	/**
	 * @desc 查询某一段时间内，发表的交流记录
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<ShopChat> findChatByDate(Date startDate,Date endDate);
	
	public List<Message> findChatByFromTo(String from_id, String to_id);
	
	public List<String> findChatByFromOrTo(String uid);
	
	public boolean updateChatStatus(String id);
	

}
