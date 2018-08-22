package com.cdut.b2p.modules.shop.controller;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * @title CustomerCenterController
 * @desc  CustomerCenterController是一个买家中兴处理类
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.SecurityUtils;
import com.cdut.b2p.modules.shop.po.ShopCart;
import com.cdut.b2p.modules.shop.po.ShopCollection;
import com.cdut.b2p.modules.shop.po.ShopOrder;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.po.ShopWorkorder;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopCartService;
import com.cdut.b2p.modules.shop.service.ShopCollectionService;
import com.cdut.b2p.modules.shop.service.ShopGoodsService;
import com.cdut.b2p.modules.shop.service.ShopOrderService;
import com.cdut.b2p.modules.shop.service.ShopUserService;
import com.cdut.b2p.modules.shop.service.ShopWorkorderService;

@Controller
@RequestMapping("${shopPath}/customerCenter")
public class CustomerCenterController extends BaseController{
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private ShopOrderService shopOrderService;
	@Autowired
	private ShopGoodsService shopGoodsService;
	@Autowired
	private ShopWorkorderService shopWorkorderService;
	@Autowired
	private ShopCollectionService shopCollectionService;
	@Autowired
	private ShopCartService shopCartService;
	/**
	 * @desc 查询个人信息
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/myInfo",method=RequestMethod.POST)
	public String getMyInfo(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		String uid=(String) request.getAttribute("uid");
		ShopUser user=shopUserService.findUserById(uid);
		model.addObject("Customer", user);
		return renderString(response, model); 
	}

	/**
	 * @desc 修改个人密码
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/myPWD",method=RequestMethod.POST)
	public String myPWD(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		System.out.println("修改密码");
		String uid=(String) request.getAttribute("uid");
		String oldpwd=request.getParameter("oldPWD");
		String newpwd=request.getParameter("newPWD");
		System.out.println(uid+"\n"+oldpwd+"\n"+newpwd);
		ShopUser user=shopUserService.findUserById(uid);
		if(!SecurityUtils.getMD5(oldpwd).equals(user.getUserPassword())) {
			model.addObject("PWDMessage", "false");
			return renderString(response, model);
		}
		
		shopUserService.updatePWD(uid, newpwd);
		model.addObject("PWDMessage", "true");
		return renderString(response, model);
	}
	
	/**
	 * @desc 查询我的交易记录
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/myOrder",method=RequestMethod.POST)
	public String getMyOrder(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		String uid=(String) request.getAttribute("uid");
		List<ShopOrder> orderList=shopOrderService.findOrderByCustomer(uid);
		model.addObject("OrderList", orderList);
		return renderString(response, model); 
	}
	/**
	 * @desc 买家催单
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/reminder",method=RequestMethod.POST)
	public String reminder(HttpServletResponse response,HttpServletRequest request) {
		String id=request.getParameter("oid");
		shopOrderService.reminder(id);
		return renderString(response, "催单成功");
	}
	/**
	 * @desc 确认商品到达
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sureOrder",method=RequestMethod.POST)
	public String sureOrder(HttpServletResponse response,HttpServletRequest request) {
		String id=request.getParameter("oid");
		shopOrderService.suerOrder(id);
		return renderString(response, "确认到达");
	}
	/**
	 * @desc 删除我的订单[一或多]
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delMyOrder",method=RequestMethod.POST)
	public String delMyOrder(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		String[]oids=request.getParameterValues("oid");
		shopOrderService.deleteOrder(oids);
		model.addObject("OrderMessage", "删除成功！");
		return renderString(response, model);
	}
	
	/**
	 * @desc 投诉
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/myComplaint",method=RequestMethod.POST)
	public String myComplaint(HttpServletResponse response,HttpServletRequest request,ShopWorkorder shopWorkorder) {
		String uid=(String) request.getAttribute("uid");
		String gid=request.getParameter("gid");//从订单上获取到的商品id
		String seller_id=shopGoodsService.findSellerId(gid);
		shopWorkorder.setWorkorderUserId(uid);
		shopWorkorder.setWorkorderReceiverId(seller_id);
		shopWorkorder.setCreateDate(new Date());
		shopWorkorder.setUpdateDate(new Date());
		shopWorkorderService.addShopWorkOrder(shopWorkorder);
		//添加提示信息
		return renderString(response, "投诉成功");
	}
	/**
	 * @desc 用户添加收藏物
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/myFavorite",method=RequestMethod.POST)
	public String myFavorite(HttpServletResponse response,HttpServletRequest request) {
		String uid=(String) request.getAttribute("uid");
		String gid=request.getParameter("gid");
		shopCollectionService.addCollection(uid, gid);
		return renderString(response, "收藏成功！");
	}
	/**
	 * @desc 查询我的收藏物
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/selectMyFavorite",method=RequestMethod.POST)
	public String selectMyFavorite(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView  model=new  ModelAndView();
		String uid=(String) request.getAttribute("uid");
		List<ShopCollection> list=shopCollectionService.findCollectionByUser(uid);
		model.addObject("CollectionList", list);
		return renderString(response, model);
	}
	/**
	 * @desc 删除我的收藏物【一或多】
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delMyFavorite",method=RequestMethod.POST)
	public String delMyFavorite(HttpServletResponse response,HttpServletRequest request) {
		String[] ids=request.getParameterValues("id");
		shopCollectionService.delCollection(ids);
		return renderString(response, "删除成功");
	}
//	/**
//	 * @desc 添加商品到购物车
//	 * @param response
//	 * @param request
//	 * @return
//	 */
//	@ShopAuth
//	@RequestMapping(value="/myCart",method=RequestMethod.POST)
//	public String myCart(HttpServletResponse response,HttpServletRequest request) {
//		String uid=(String) request.getAttribute("uid");
//		String gid=request.getParameter("gid");
//		shopCartService.addCart(uid, gid);
//		return renderString(response, "添加成功");
//	}
//	/**
//	 * @desc 查询我的购物车
//	 * @param response
//	 * @param request
//	 * @return
//	 */
//	@ShopAuth
//	@RequestMapping("/selectMyCart")
//	public String selectMyCart(HttpServletResponse response,HttpServletRequest request) {
//		String uid=(String) request.getAttribute("uid");
//		ModelAndView model=new ModelAndView();
//		List<ShopCart> list=shopCartService.findCartByUser(uid);
//		model.addObject("CartList", list);
//		return renderString(response, model);
//	}
//	/**
//	 * @desc 删除我的购物车
//	 * @param response
//	 * @param request
//	 * @return
//	 */
//	public String delMyCart(HttpServletResponse response,HttpServletRequest request) {
//		String[]ids=request.getParameterValues("gid");
//		shopCartService.delCart(ids);
//		return renderString(response, "删除成功");
//	}
}
