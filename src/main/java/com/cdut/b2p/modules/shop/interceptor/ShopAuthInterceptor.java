package com.cdut.b2p.modules.shop.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cdut.b2p.common.security.TokenManger;
import com.cdut.b2p.common.utils.CacheUtils;
import com.cdut.b2p.common.utils.HttpHeaderUtils;
import com.cdut.b2p.common.utils.JsonUtils;
import com.cdut.b2p.common.utils.StringUtils;
import com.cdut.b2p.modules.shop.security.annotation.ShopAuth;

public class ShopAuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		boolean flag = false;
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			ShopAuth auth = ((HandlerMethod) handler).getMethodAnnotation(ShopAuth.class);
			if (auth == null || auth.validate() == false) {
				flag = true;
			} else {
				String token = HttpHeaderUtils.getHeader(request, "cdutb2p_shop_token");
				if (token != null && !StringUtils.isBlank(token)) {
					String uid = TokenManger.getAppUID(token);
					if (uid != null || !StringUtils.isBlank(uid)) {
						flag = true;
						request.setAttribute("uid", uid);
					}
				} else {
					flag = false;
				}
			}
		} else {
			flag = true;
		}
		if(!flag) {
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("status", "1");
			map.put("msg", "用户未登陆");
			map.put("success", false);
			response.getWriter().print(JsonUtils.toJsonString(map));
		}
		return flag;
	}
}
