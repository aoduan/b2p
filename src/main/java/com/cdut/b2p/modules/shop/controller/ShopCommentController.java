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
import com.cdut.b2p.modules.shop.po.ShopComment;
import com.cdut.b2p.modules.shop.po.ShopCommentVo;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopCommentService;

@Controller
@RequestMapping("${shopPath}/comment")
public class ShopCommentController extends BaseController{
	@Autowired
	private ShopCommentService shopCommentService;
	/**
	 * @desc 查询某件商品下，所有的用户评论信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAll")
	public String getAllByGoodsId(HttpServletResponse response,HttpServletRequest request) {
		System.out.println("进入评论查询");
		ModelAndView model=new ModelAndView();
		//获取商品id
		String gid=request.getParameter("goods_id");
		System.out.println("商品id："+gid);
		List<ShopCommentVo> list=shopCommentService.selectCommentByGId(gid);
		model.addObject("ShopCommentList", list);
		return renderString(response, model);
	}
	/**
	 * @desc 用户发表评论
	 * @param response
	 * @param request
	 * @return
	 */
	@ShopAuth
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	public String addComment(HttpServletResponse response,HttpServletRequest request) {
		String uid=(String) request.getAttribute("uid");
		String gid=request.getParameter("goods_id");
		String message=request.getParameter("message");
		System.out.println("发表评论："+uid+"\n"+gid+"\n"+message);
		ShopComment comment=new ShopComment();
		comment.setCommentGoodsId(gid);
		comment.setCommentUserId(uid);
		comment.setCommentMessage(message);
		shopCommentService.addComment(comment);
		return renderString(response, "添加成功");
	}
	
	
}
