package com.cdut.b2p.modules.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.modules.shop.mapper.ShopChatMapper;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.po.ShopChatExample;
import com.cdut.b2p.modules.shop.po.ShopComment;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.service.ShopChatService;
import com.cdut.b2p.modules.shop.service.ShopUserService;
import com.cdut.b2p.modules.shop.websocket.po.Message;
import com.cdut.b2p.modules.shop.websocket.task.ChatTaskHandler;


@Service
@Transactional
public class ShopChatServiceImpl implements ShopChatService{

	@Autowired
	private ShopChatMapper shopChatMapper;
	@Autowired
	private ShopUserService shopUserService;
	/**
	 * @desc  添加一条聊天记录
	 * @param shopChat
	 * @return boolean
	 */
	@Transactional(readOnly=false)
	@Override
	public boolean saveChat(ShopChat shopChat) {
		ShopChat chat = findChatById(shopChat.getId());
		int count = 0;
		count = shopChatMapper.insertSelective(shopChat);
		
		CacheUtils.remove("chat_from_"+shopChat.getChatFromId()+"_to_" + shopChat.getChatToId());
		CacheUtils.remove("chat_id_" + shopChat.getId());
		if(count>0) {
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly=false)
	@Override
	public boolean updateChatStatus(String id) {
		ShopChat shopChat = findChatById(id);
		if(shopChat != null) {
			shopChat.setChatStatus("1");
			shopChatMapper.updateByPrimaryKeyWithBLOBs(shopChat);
			CacheUtils.remove("chat_from_"+shopChat.getChatFromId()+"_to_" + shopChat.getChatToId());
			CacheUtils.remove("chat_id_" + shopChat.getId());
			return true;
		}
		return false;
	}
	/**
	 * @desc 根据id，查询相应的聊天记录
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public ShopChat findChatById(String id) {
		ShopChat chat = (ShopChat) CacheUtils.get("chat_id_" + id);
		if(chat == null) {
			chat = shopChatMapper.selectByPrimaryKey(id);
			CacheUtils.put("chat_id_" + id, chat);
		}
		
		return chat;
	}
	/**
	 * @desc 根据用户id，查询其所发表的交流
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopChat> findChatByUser(String uid) {
		ShopChatExample example=new ShopChatExample();
		example.or().andChatFromIdEqualTo(uid);
		List<ShopChat> list=shopChatMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	/**
	 * @desc 查询所有的交流记录
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopChat> findAllChat() {
		ShopChatExample example=new ShopChatExample();
		List<ShopChat> list=shopChatMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	/**
	 * @desc 查询某一段时间内，发表的交流记录
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ShopChat> findChatByDate(Date startDate, Date endDate) {
		ShopChatExample example=new ShopChatExample();
		example.or().andCreateDateBetween(startDate, endDate);
		List<ShopChat> list=shopChatMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Message> findChatByFromTo(String from_id, String to_id) {
		List<Message> list = (List<Message>) CacheUtils.get("chat_cache_" 
				+ from_id +"_"+ to_id);
		if(list == null) {
			list = new ArrayList<Message>();
			
			ShopChatExample sce = new ShopChatExample();
			sce.or().andChatFromIdEqualTo(from_id).andChatToIdEqualTo(to_id);
			List<ShopChat> list1 = shopChatMapper.selectByExampleWithBLOBs(sce);
			
			ShopUser from = shopUserService.findUserById(from_id);
			ShopUser to = shopUserService.findUserById(to_id);
			
			for(ShopChat chat : list1) {
				Message msg = new Message();
				msg.setFrom_id(from_id);
				msg.setTo_id(to_id);
				msg.setDate(chat.getCreateDate());
				msg.setMsg(chat.getChatMessage());
				msg.setType(chat.getChatType());
				msg.setFrom_img(from.getUserImage());
				msg.setFrom_name(from.getUserNickname());
				msg.setTo_name(to.getUserNickname());
				msg.setTo_img(to.getUserImage());
				msg.setStatus(chat.getChatStatus());
				msg.setId(chat.getId());
				
				
				list.add(msg);
			}
		}
		for(Message e : list) {
			if(e.getStatus().equals("0")) {
				
				ChatTaskHandler.andUpdate(e.getId());
			}
		}
		return list;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<String> findChatByFromOrTo(String uid) {
		
		List<String> list1 = (List<String>) CacheUtils.get("chat_from_or_to_" + uid);
		
		if(list1 == null) {
			ShopChatExample example=new ShopChatExample();
			ShopChatExample.Criteria cri1 = example.createCriteria();
			cri1.andChatFromIdEqualTo(uid);
			
			ShopChatExample.Criteria cri2 = example.createCriteria();
			cri2.andChatToIdEqualTo(uid);
		
			example.or(cri2);
			
			List<ShopChat> list = shopChatMapper.selectByExample(example);
			
			list1 = new ArrayList<String>();
			for(ShopChat chat : list) {
				if(!list1.contains(chat.getChatFromId())) {
					list1.add(chat.getChatFromId());
				}
				if(!list1.contains(chat.getChatToId())) {
					list1.add(chat.getChatToId());
				}
			}
			CacheUtils.put("chat_from_or_to_" + uid, list1);
			
		}
		return list1;
	}

	
	


}
