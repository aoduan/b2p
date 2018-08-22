package com.cdut.b2p.modules.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @title SysChatController
 * @desc SysChatController类是有关交流的处理类
 * @author zsb
 *
 */
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.utils.DateUtils;
import com.cdut.b2p.modules.shop.po.ShopChat;
import com.cdut.b2p.modules.shop.service.ShopChatService;
@Controller
@RequestMapping("${sysPath}/admin/chat")
public class SysChatController extends BaseController{
	@Autowired
	private ShopChatService shopChatService;
	/**
	 * @desc 获取所有的订单
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.POST)
	public String getAllChat(HttpServletResponse response) {
		ModelAndView model=new ModelAndView();
		List<ShopChat> list=shopChatService.findAllChat();
		model.addObject("ChatList", list);
		return renderString(response, model);
	}
	/**
	 * @desc 获取某一时间段内的聊天记录
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/selectByDate",method=RequestMethod.POST)
	public String getChatByDate(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		Date startDate=DateUtils.parseDate(request.getParameter("startDate"));
		Date endDate=DateUtils.parseDate(request.getParameter("endDate"));
		List<ShopChat> list=shopChatService.findChatByDate(startDate, endDate);
		model.addObject("ChatList2", model);
		return renderString(response, model);
	}
	
}
