package com.cdut.b2p.modules.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.service.ShopChatService;

@Controller
@RequestMapping("${shopPath}/")
public class ShopChatController extends BaseController{
	@Autowired
	private ShopChatService shopchatservice;
	//发表留言
	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public String addMessage(ShopChat shopchat,Model model) {
			shopchatservice.addMessage(shopchat);
			return "";
	}
			
	//查询留言
	@RequestMapping(value = "findMessage", method = RequestMethod.POST)
	public String findMessge(String goodsid,Model model) {
		shopchatservice.findMessagebyId(goodsid);
		return "";
	}
	//删除留言
	@RequestMapping(value = "deleteMessge", method = RequestMethod.POST)
	public String deleteMessge(String userid,Model model) {
		shopchatservice.deleteMessagebyId(userid);
		return "delete";
	}
	

}
