package com.cdut.b2p.modules.sys.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.common.utils.IdUtils;
import com.cdut.b2p.modules.sys.mapper.SysLogMapper;
import com.cdut.b2p.modules.sys.po.SysLog;
import com.cdut.b2p.modules.sys.service.SysLogService;

/**
 * @title  SysLogServiceImpl
 * @desc   SysLogServiceImpl是SysLogService服务接口的实现类
 * @author zsb
 * @Date   2018/8/14
 *
 */

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService{
	@Autowired
	private SysLogMapper sysLogMapper;
	/**
	 * @desc 添加一条管理员登录日志记录
	 * @param 请求url地址
	 * @param remoteAddr请求地址
	 * @return
	 */
	@Override
	public int addSysLog(String url,String remoteAddr) {
		SysLog sysLog=new SysLog();
		sysLog.setLogRemoteAddr(remoteAddr);
		sysLog.setLogRequestUri(url);
		sysLog.setCreateDate(new Date());
		sysLog.setId(IdUtils.randomBase62(64));
		//sysLog.setCreateBy("");
		//sysLog.setLogException("");
		sysLog.setLogMethod("POST");
		//sysLog.setLogParams("");
		//sysLog.setLogTitle("");
		//sysLog.setLogType("");
		return sysLogMapper.insertSelective(sysLog);
	}

}
