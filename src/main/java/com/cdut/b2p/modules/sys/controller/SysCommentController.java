package com.cdut.b2p.modules.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @title SysCommentController
 * @desc SysCommentController类是有关用户评论的处理类
 * @author zsb
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.utils.DateUtils;
import com.cdut.b2p.modules.shop.po.ShopComment;
import com.cdut.b2p.modules.shop.service.ShopCommentService;
@Controller
@RequestMapping("${sysPath}/admin/comment")
public class SysCommentController extends BaseController{
	@Autowired
	private ShopCommentService shopCommentService;
	/**
	 * @desc 获取所有的订单记录
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.POST)
	public String getAllComment(HttpServletResponse response) {
		ModelAndView model=new ModelAndView();
		List<ShopComment> list=shopCommentService.findAllComment();
		model.addObject("CommentList", list);
		return renderString(response, model);
	}
	/**
	 * @desc  获取某一段时间内的生成的订单
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/selectByDate",method=RequestMethod.POST)
	public String getCommentByDate(HttpServletResponse response,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		Date startDate=DateUtils.parseDate(request.getParameter("startDate"));
		Date endDate=DateUtils.parseDate(request.getParameter("endDate"));
		List<ShopComment> list=shopCommentService.findCommentByDate(startDate, endDate);
		model.addObject("CommentList2", model);
		return renderString(response, model);
	}
}