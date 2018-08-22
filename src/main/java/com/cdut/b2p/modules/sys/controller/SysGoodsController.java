package com.cdut.b2p.modules.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @title SysGoodsController
 * @desc SysGoodsController类是有关商品的处理类
 * @author zsb
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.utils.DateUtils;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
@Controller
@RequestMapping("${sysPath}/admin/goods")
public class SysGoodsController extends BaseController{
	@Autowired
	private ShopGoodsService shopGoodsService;
	/**
	 * @desc 查询所有的商品集 
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.POST)
	public String getAllGoods(HttpServletResponse response) {
		ModelAndView model=new ModelAndView();
		List<ShopGoods> list=shopGoodsService.findAllGoods();
		model.addObject("GoodsList", list);
		return renderString(response, model);
	}
	/**
	 * @desc 查询某一时间段内上架的商品
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectByDate",method=RequestMethod.POST)
	public String getGoodsByDate(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		Date startDate=DateUtils.parseDate(request.getParameter("startDate"));
		Date endDate=DateUtils.parseDate(request.getParameter("endDate"));
		List<ShopGoods> list=shopGoodsService.findGoodsByDate(startDate, endDate);
		model.addObject("GoodsList2", list);
		return renderString(response, model);
	}
}
