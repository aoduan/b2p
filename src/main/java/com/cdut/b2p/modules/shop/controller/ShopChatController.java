package com.cdut.b2p.modules.shop.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdut.b2p.common.controller.BaseController;

import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.service.ShopChatService;

import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopChatService;
import com.cdut.b2p.modules.shop.service.ShopUserService;
import com.cdut.b2p.modules.shop.websocket.po.Message;


@Controller
@RequestMapping("${shopPath}/")

public class ShopChatController extends BaseController {
	
	@Autowired
	private ShopChatService shopChatService;
	
	@Autowired
	private ShopUserService shopUserService;

	@ShopAuth
	@RequestMapping(value = "chat/message/get", method = RequestMethod.POST)
	public String getMessage(HttpServletRequest request, HttpServletResponse response,String
			from_id) {
		
		String uid = (String) request.getAttribute("uid");
		List<Message>  list = shopChatService.findChatByFromTo(from_id, uid);
		List<Message>  list1 = shopChatService.findChatByFromTo(uid, from_id);
		
		List<Message> list2 = new ArrayList<Message>();
		
		if(list != null && list1 != null) {
			for(Message e : list) {
				list2.add(e);
			}
			for(Message e : list1) {
				list2.add(e);
			}
			return renderSuccessString(response, "获取消息成功",list2);
		}
		else if(list == null && list1 != null) {
			return renderSuccessString(response, "获取消息成功",list1);
		}
		else if(list != null && list1 == null) {
			return renderSuccessString(response, "获取消息成功",list);
		}
		return renderErrorString(response, "");

	}
	
	@ShopAuth
	@RequestMapping(value = "chat/message/list", method = RequestMethod.POST)
	public String getMessage1(HttpServletRequest request, HttpServletResponse response) {
		String uid = (String) request.getAttribute("uid");
		
		ShopUser user = shopUserService.findUserById(uid);
		List<String> list = shopChatService.findChatByFromOrTo(uid);
		List<Message> list1 = new ArrayList<Message>();
		for(String s : list) {
			Message msg = new Message();
			ShopUser u =  shopUserService.findUserById(s);
			if(!s.equals(uid)) {
				msg.setTo_id(s);
				msg.setTo_img(u.getUserImage());
				msg.setTo_name(u.getUserNickname());
				list1.add(msg);
			}
		}
		return renderSuccessStringAndUserId(response, "获取列表成功",list1,user);
	}



}
