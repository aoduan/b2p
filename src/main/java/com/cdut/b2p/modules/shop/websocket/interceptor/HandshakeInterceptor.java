package com.cdut.b2p.modules.shop.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.cdut.b2p.common.security.TokenManger;
import com.cdut.b2p.common.utils.HttpHeaderUtils;
import com.cdut.b2p.common.utils.StringUtils;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{
	 
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		ServletServerHttpRequest req = (ServletServerHttpRequest) request;
		String token = req.getServletRequest().getParameter("token");
		if (token != null && !StringUtils.isBlank(token)) {
			String uid = TokenManger.getAppUID(token);
			if (uid != null || !StringUtils.isBlank(uid)) {
				attributes.put("uid", uid);
				return super.beforeHandshake(request, response, wsHandler, attributes);
			}
		} else {
			return false;
		}
		return false;
		
	}
	
	
 
	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		
		super.afterHandshake(request, response, wsHandler, ex);
	}
 


}
