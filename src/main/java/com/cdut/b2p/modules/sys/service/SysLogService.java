package com.cdut.b2p.modules.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cdut.b2p.modules.sys.po.SysLog;

/**
 * @title SysLogService
 * @Desc  SysLogService是实现管理员登录日志记录的服务接口
 * @author zsb
 * @Date   2018/8/14
 *
 */

public interface SysLogService {
	/**
	 * @desc 添加一条管理员登录日志记录
	 * @param 请求url地址
	 * @param remoteAddr请求地址
	 * @return
	 * 
	 */
	public int addSysLog(String url,String remoteAddr);

}
