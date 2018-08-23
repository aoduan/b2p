package com.cdut.b2p.modules.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.modules.shop.po.ShopAddress;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopAddressService;

/**
 * @desc ShopAddressController用于处理有关收获地址的处理
 * @author zsb
 *
 */
@Controller
@RequestMapping("${shopPath}/address")
public class ShopAddressController extends BaseController{
	@Autowired
	private ShopAddressService shopAddressService;
	/**
	 * @desc 添加新的收件地址
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAddress(HttpServletResponse response,HttpServletRequest request,ShopAddress shopAddress) {
		System.out.println("ShopAddress:"+shopAddress);
		String uid=(String) request.getAttribute("uid");
		shopAddress.setId(IdUtils.uuid());
		shopAddress.setAddressUserId(uid);
		shopAddressService.addAddress(shopAddress);
		return renderString(response, shopAddress.getId());
	}
	/**
	 * @desc 获取指定用户的所有收货地址【默认展示4个最新地址】
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/getAll",method=RequestMethod.POST)
	public String geAll(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		String uid=(String) request.getAttribute("uid");
		List<ShopAddress> list=shopAddressService.findAddressByUser(uid);
		model.addObject("AddressList", list);
		return renderString(response, model);
	}
}
