package com.cdut.b2p.modules.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.po.Page;
import com.cdut.b2p.common.utils.AreaUtils;
import com.cdut.b2p.common.utils.AreaUtils.TreeNode;
import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopGoodsInfo;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.shop.service.ShopUserService;
import com.cdut.b2p.modules.sys.po.SysArea;
import com.cdut.b2p.modules.sys.po.SysDict;
import com.cdut.b2p.modules.sys.service.SysAreaService;
import com.cdut.b2p.modules.sys.service.SysDictService;

@Controller
@RequestMapping("${shopPath}/")
public class ShopCustomerController extends BaseController{
	@Autowired
	private ShopUserService shopUserService;
	
	@Autowired
	private ShopGoodsService shopGoodsService;
	
	@Autowired
	private SysAreaService sysAreaService;
	
	@Autowired
	private SysDictService sysDictService;
	
	
	/**
	 * 获取商品展示
	 */
	@RequestMapping(value = "goods/recommend", method = RequestMethod.POST)
	public String recommend(HttpServletRequest request, HttpServletResponse response) {
		
		String uid = (String) request.getAttribute("uid");
		List<ShopGoodsInfo> list = shopGoodsService.findRecommendGoods();
    	return renderSuccessString(response, "获取商品成功",list);
	}

	/**
	 * 获取商品展示
	 */
	@RequestMapping(value = "goods/info", method = RequestMethod.POST)
	public String info(HttpServletRequest request, HttpServletResponse response,String goods_id) {
		ShopGoodsInfo info = shopGoodsService.findGoodsByGoodsId(goods_id);
		
    	return renderSuccessString(response, "获取商品成功", info);
	}
	
	/**
	 * 获取商品列表
	 */
	@RequestMapping(value = "goods/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, HttpServletResponse response,
			String type,String brand,Integer min_price,Integer max_price,
			String area,Integer pageNum,Integer pageSize,String keyword) {
		
		 Page<ShopGoodsInfo> pages = shopGoodsService.findGoodsofOnePage(type, brand, min_price,
				max_price, area, pageNum, pageSize,keyword);
		 
    	return renderSuccessString(response, "获取商品成功", pages);
	}
	
	/**
	 * 获取地区
	 */
	@RequestMapping(value = "goods/area/list", method = RequestMethod.POST)
	public String area(HttpServletRequest request, HttpServletResponse response) {
		List<SysArea> list = sysAreaService.findAllArea();
		List<TreeNode> list1 = AreaUtils.parseArea(list,"0");
		return renderSuccessString(response, "获取地区数据成功", list1);
	}
	
	/**
	 * 获取地区
	 */
	@RequestMapping(value = "goods/area", method = RequestMethod.POST)
	public String area1(HttpServletRequest request, HttpServletResponse response,String areaid) {
		List<SysArea> list = sysAreaService.findAllChildrenByParentId(areaid);
		List<TreeNode> list1 = AreaUtils.parseArea(list,areaid);
		return renderSuccessString(response, "获取地区数据成功", list1);
	}
	
	/**
	 * 获取商品品牌
	 */
	@RequestMapping(value = "goods/brand", method = RequestMethod.POST)
	public String brand(HttpServletRequest request, HttpServletResponse response,String type) {
    	List<SysDict> list = sysDictService.findAllDictLabelByDictType(type);
    	return renderSuccessString(response, "获取品牌数据成功", list);

	}


}
