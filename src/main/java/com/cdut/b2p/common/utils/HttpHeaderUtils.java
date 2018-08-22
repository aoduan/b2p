package com.cdut.b2p.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHeaderUtils {
	public static String getHeader(HttpServletRequest request,String name) {
		return request.getHeader(name);

	}

	public static void setHeader(HttpServletResponse response,String name,String value) {
		response.setHeader(name, value);

	}

}