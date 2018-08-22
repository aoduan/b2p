package com.cdut.b2p.modules.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdut.b2p.common.config.Global;
import com.cdut.b2p.common.controller.BaseController;
import com.cdut.b2p.common.security.TokenManger;
import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.common.utils.EmailUtils;
import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.common.utils.MacUtils;
import com.cdut.b2p.common.utils.NetWorkUtils;
import com.cdut.b2p.common.utils.SecurityUtils;
import com.cdut.b2p.common.utils.StringUtils;
import com.cdut.b2p.common.utils.TimestampUtils;
import com.cdut.b2p.common.utils.ValidateUtils;
import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;
import com.cdut.b2p.modules.shop.service.ShopUserService;

@Controller
@RequestMapping("${shopPath}/")
public class ShopUserController extends BaseController {

	@Autowired
	private ShopUserService shopUserService;

	/**
	 * 用户信息
	 */
	@ShopAuth
	@RequestMapping(value = "user/islogin", method = RequestMethod.POST)
	public String isLogin(HttpServletRequest request, HttpServletResponse response) {
		String uid = (String) request.getAttribute("uid");
		ShopUser u = shopUserService.findUserById(uid);
		return renderSuccessString(response, "已经登陆", u);

	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) {

		ShopUser u = shopUserService.findUserByUsername(username);
		if (u == null || !ValidateUtils.validateUsername(username) || !ValidateUtils.validatePwd(password)) {
			return renderErrorString(response, "用户名不存在");
		}
		if (!u.getUserPassword().equals(SecurityUtils.getMD5(password))) {
			return renderErrorString(response, "密码错误");
		}
		return renderTokenString(response, u.getId(), "登陆成功");

	}

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "user/reg", method = RequestMethod.POST)
	public String reg(HttpServletRequest request, HttpServletResponse response, String emailcode, String email,
			String username, String password, String nickname) {

		ShopUser u = shopUserService.findUserByUsername(username);
		if (u != null && !StringUtils.isBlank(u.getUserName())) {
			return renderErrorString(response, "用户名已被注册");
		}
		ShopUser u1 = shopUserService.findUserByNickname(nickname);
		if (u1 != null && !StringUtils.isBlank(u1.getUserNickname())) {
			return renderErrorString(response, "昵称已被注册");
		}

		try {
			String mac = MacUtils.getMac();
			String ip = NetWorkUtils.getIpAddress(request);
			String id = ip + mac + "regEmailCode";
			String code = (String) CacheUtils.get(id);
			String p_email = (String) CacheUtils.get(id + "email");
			if (code != null && code.equals(emailcode) && email.equals(p_email)
					&& ValidateUtils.validateUsername(username) && ValidateUtils.validatePwd(password)
					&& ValidateUtils.validateText(nickname, 2, 8) && ValidateUtils.validateEamil(email)) {

				CacheUtils.remove(id);
				shopUserService.regUser(username, password, nickname, email);
			} else {
				return renderErrorString(response, "注册失败,请检查");
			}

		} catch (Exception e) {
			return renderErrorString(response, "注册失败");
		}
		return renderSuccessString(response, "注册成功");
	}

	/**
	 * 用户注册时发送
	 */
	@RequestMapping(value = "user/reg/sendEmail", method = RequestMethod.POST)
	public String sendEmail(HttpServletRequest request, HttpServletResponse response, String email) {
		if (!ValidateUtils.validateEamil(email)) {
			return renderErrorString(response, "该邮箱已被注册");
		}
		ShopUser u = shopUserService.findUserByEmail(email);
		if (u != null && !StringUtils.isBlank(u.getUserEmail())) {
			return renderErrorString(response, "该邮箱已被注册");
		}
		try {
			String mac = MacUtils.getMac();
			String ip = NetWorkUtils.getIpAddress(request);
			String id = ip + mac + "regEmailCode";
			String code = (String) CacheUtils.get(id);
			if (code != null && !StringUtils.isBlank(code)) {
				Long t = (Long) CacheUtils.get(id + "time");
				if (!TimestampUtils.IsAfter(t)) {
					return renderErrorString(response, "已经发送邮件,请等待五分钟后在发送");
				}
			}
			String regEmailCode = StringUtils.genRandomStringOfInt(6);
			Long time = TimestampUtils.timeAfter(5 * 60 * 1000);
			CacheUtils.put(id, regEmailCode);
			CacheUtils.put(id + "time", time);
			CacheUtils.put(id + "email", email);
			EmailUtils.Send(email, regEmailCode, "注册验证码");

		} catch (Exception e) {
			return renderErrorString(response, "发送邮件失败");
		}
		return renderSuccessString(response, "发送邮件成功");
	}

	@RequestMapping(value = "user/find_password/validate_user", method = RequestMethod.POST)
	public String validate_user(HttpServletRequest request, HttpServletResponse response, String email,
			String username) {
		if (!ValidateUtils.validateEamil(email)) {
			return renderErrorString(response, "验证未通过");
		}
		if (!ValidateUtils.validateUsername(username)) {
			return renderErrorString(response, "验证未通过");
		}
		ShopUser u = shopUserService.findUserByEmail(email);
		if (u != null && u.getUserName().equals(username)) {
			try {
				String code_pre = (String) CacheUtils.get("password_" + email);
				if (code_pre != null && !StringUtils.isBlank(code_pre)) {
					Long t = (Long) CacheUtils.get("password_" + email + "_time");
					if (!TimestampUtils.IsAfter(t)) {
						return renderErrorString(response, "已经发送邮件,请等待五分钟后在发送");
					}
				}
				
				String code = TokenManger.createToken(email);
				String contextpath = request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath() + "/static";
				String url = contextpath + Global.getShopPath() + "/set_password.html" + "?email=" + email + "&code="
						+ code;
				Long time = TimestampUtils.timeAfter(5 * 60 * 1000);
				CacheUtils.put("password_" + email + "_time", time);
				CacheUtils.put("password_" + email, code);
				EmailUtils.Send(email, url, "访问连接,设置新密码");
				return renderSuccessString(response, "验证通过,请根据邮箱连接操作.");

			} catch (Exception e) {
				return renderErrorString(response, "验证未通过");
			}
		}
		return renderErrorString(response, "验证未通过");

	}

	@RequestMapping(value = "user/find_password/set_password", method = RequestMethod.POST)
	public String set_password(HttpServletRequest request, HttpServletResponse response
			,String email, String code, String password) {
		if (!ValidateUtils.validatePwd(password)) {
			return renderErrorString(response, "修改失败");
		}
		String code_pre = (String) CacheUtils.get("password_" + email);
		if (code_pre != null && code_pre.equals(code) &&  email.equals(TokenManger.getAppUID(code))) {
			ShopUser u = shopUserService.findUserByEmail(email);
			u.setUserPassword(SecurityUtils.getMD5(password));
			shopUserService.updateUser(u);
			CacheUtils.remove("password_" + email);
			CacheUtils.remove("password_" + email + "_time");
			return renderSuccessString(response, "修改成功");
		}
		return renderErrorString(response, "修改失败");
	}

	@RequestMapping(value = "user/find_password/validate_code", method = RequestMethod.POST)
	public String validate_code(HttpServletRequest request, HttpServletResponse response, String email, String code) {
		String code_pre = (String) CacheUtils.get("password_" + email);
		if (code_pre != null && code_pre.equals(code) &&  email.equals(TokenManger.getAppUID(code))) {
			return renderSuccessString(response, "");
		}
		return renderErrorString(response, "");
	}
}
