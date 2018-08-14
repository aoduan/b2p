package com.cdut.b2p.modules.shop.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.service.ShopUserService;
import com.cdut.b2p.modules.shop.service.impl.TestService;
import com.cdut.b2p.modules.shop.utils.ShopCacheUtils;

@Controller
public class ShopUserController extends BaseController{

	@Autowired
	private ShopUserService shopUserService;
	
	@Autowired
	private TestService testService;
	/**
	 * 用户登录
	 */
	@RequestMapping(value = "${shopPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model
			) {

		
		
		
		//model.addAttribute("1","1");
		testService.start();
		return renderString(response, model);

	}

}
