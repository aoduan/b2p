package com.cdut.b2p.common.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdut.b2p.common.utils.JsonUtils;
import com.cdut.b2p.common.security.TokenManger;
import com.cdut.b2p.common.utils.DateUtils;

public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${sysPath}")
	protected String adminPath;
	
	/**
	 * 前端基础路径
	 */
	@Value("${shopPath}")
	protected String shopPath;
	
	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;
	
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderErrorString(HttpServletResponse response, String msg) {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.clear();
		map.put("msg", msg);
		map.put("success", false);
		return renderString(response, JsonUtils.toJsonString(map), "application/json");
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderSuccessString(HttpServletResponse response, String msg) {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.clear();
		map.put("msg", msg);
		map.put("success", true);
		return renderString(response, JsonUtils.toJsonString(map), "application/json");
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderSuccessString(HttpServletResponse response, String msg, Object data) {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.clear();
		map.put("data", data);
		map.put("msg", msg);
		map.put("success", true);
		return renderString(response, JsonUtils.toJsonString(map), "application/json");
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderSuccessStringAndUserId(HttpServletResponse response, String msg, Object data,Object user) {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.clear();
		map.put("data", data);
		map.put("msg", msg);
		map.put("success", true);
		map.put("user", user);
		return renderString(response, JsonUtils.toJsonString(map), "application/json");
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderTokenString(HttpServletResponse response, String uid, String msg){
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.clear();
		map.put("msg", msg);
		map.put("success", true);
		map.put("token", TokenManger.createToken(uid));
		return renderString(response, JsonUtils.toJsonString(map), "application/json");
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonUtils.toJsonString(object), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}

		});
	}
	
}
