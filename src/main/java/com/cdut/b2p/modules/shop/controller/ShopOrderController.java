package com.cdut.b2p.modules.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsInfo;
import com.cdut.b2p.modules.shop.po.ShopOrder;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.shop.service.ShopOrderService;

/**
 * @desc ShopOrderControler处理有关订单的处理类
 * @author zsb
 *
 */
@Controller
@RequestMapping("${shopPath}/order")
public class ShopOrderController extends BaseController{
	@Autowired
	private ShopOrderService shopOrderService;
	@Autowired
	private ShopGoodsService shopGoodsService;
	/**
	 * @desc 根据商品id，查询商品的详细信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findGoodsById",method=RequestMethod.POST)
	public String findGoods(HttpServletResponse response,HttpServletRequest request) {
		System.out.println("根据商品id，查询商品的详细信息");
		String goods_id=request.getParameter("goods_id");
		ModelAndView model=new ModelAndView();
		ShopGoodsInfo goods=shopGoodsService.findGoodsByGoodsId(goods_id,false);
		model.addObject("ShopGoods", goods);
		return renderString(response, model);
	}
	
	/**
	 * @desc 生成一个订单
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/toOrder",method=RequestMethod.POST)
	public String order(HttpServletResponse response,HttpServletRequest request) {
		System.out.println("购买商品");
		ModelAndView model=new ModelAndView();
		String uid=(String) request.getAttribute("uid");
		String gid=request.getParameter("goods_id");
		ShopOrder order=shopOrderService.addOrder(uid, gid);
		model.addObject("ShopOrder", order);
		return renderString(response, model);
	}
}
