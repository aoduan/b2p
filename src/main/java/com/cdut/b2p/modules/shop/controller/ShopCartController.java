package com.cdut.b2p.modules.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.modules.shop.po.ShopCart;
import com.cdut.b2p.modules.shop.po.ShopCartVo;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsInfo;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopCartService;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.shop.service.ShopUserService;


@Controller
@RequestMapping("${shopPath}/Cart")
public class ShopCartController extends BaseController{
	@Autowired
	private ShopCartService shopCartService;
	
	/**
	 * @desc 添加商品到购物车
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/myCart",method=RequestMethod.POST)
	public String myCart(HttpServletResponse response,HttpServletRequest request) {
		String uid=(String) request.getAttribute("uid");
		String gid=request.getParameter("gid");
		shopCartService.addCart(uid, gid);
		return renderString(response, "添加成功");
	}
	/**
	 * @desc 查询我的购物车
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping("/selectMyCart")
	public String selectMyCart(HttpServletResponse response,HttpServletRequest request) {
		String uid=(String) request.getAttribute("uid");
		ModelAndView model=new ModelAndView();
		List<ShopCart> list=shopCartService.findCartByUser(uid);
		model.addObject("CartList", list);
		return renderString(response, model);
	}
	/**
	 * @desc 删除我的购物车
	 * @param response
	 * @param request
	 * @return
	 */
	public String delMyCart(HttpServletResponse response,HttpServletRequest request) {
		String[]ids=request.getParameterValues("gid");
		shopCartService.delCart(ids);
		return renderString(response, "删除成功");
	}
	
//	@Autowired
//	private ShopCartService shopcartservice;
//	@Autowired
//	private ShopGoodsService shopgoodsservice;
//	 /** 根据用户id查询购物车
//	  * 
//	  */
//	@ShopAuth
//	@RequestMapping(value="/myCart",method=RequestMethod.POST)
//	public String getCart(HttpServletRequest request, HttpServletResponse response)
//	{	
//		ShopCartVo scv=new ShopCartVo();
//		String uid = (String) request.getAttribute("uid");
//		List<ShopCart> list1=shopcartservice.findCartByUser(uid);
//		List<ShopCartVo> list2=new ArrayList();
//		for(int i=0;i<list1.size();i++)
//		{	
//			
//			scv.setGoodsBrand(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsBrand());
//			scv.setGoodsBrandModel(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsBrandModel());
//			scv.setGoodsNums(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsNums());
//			scv.setGoodsOldLevel(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsOldLevel());
//			scv.setGoodsOriginalPrice(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsOriginalPrice());
//			scv.setGoodsPics(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsPics());
//			scv.setGoodsPresentPrice(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsPresentPrice());
//			scv.setGoodsStatus(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsStatus());
//			scv.setGoodsTitle(shopgoodsservice.findGoodsByGoodsId(list1.get(i).getCartGoodsId()).getGoodsTitle());
//			list2.add(scv);
//		
//		}
//		
//		return renderSuccessString(response, "获取列表成功",list2);
//		
//	}
//	/**
//	 * 根据商品id删除购物车内容
//	 */
//	@ShopAuth
//	@RequestMapping(value="/deleteCart",method=RequestMethod.POST)
//	public String deleteCart(HttpServletRequest request, HttpServletResponse response) {
//		String[] ids=request.getParameterValues("id");
//		shopcartservice.delCart(ids);
//		return renderString(response, "删除成功");
//		
//		
//	}
}
