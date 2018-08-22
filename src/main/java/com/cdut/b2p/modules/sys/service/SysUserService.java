package com.cdut.b2p.modules.sys.service;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.modules.sys.po.SysUser;

/**
 * @title  SysUserService
 * @desc   SysUserService是用于操作管理员的服务接口
 * @author zsb
 * @Date   2018/8/14
 */
public interface SysUserService {
	/**
	 * @desc 通过用户输入的信息，进行查询该用户【管理员】是否存在
	 * @return SysUser
	 */
	public SysUser findSysUser(SysUser sysUser);
	/**
	 * @desc 通过用户id，查询指定用户信息
	 * @param id
	 * @return SysUser
	 */
	public SysUser findSysUserById(String id);
	/**
	 * @desc 查询某一时间段内，注册的用户
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SysUser> findSysUserByDate(Date startDate,Date endDate);
	/**
	 * @desc 查询系统所有的用户
	 * @return
	 */
	public List<SysUser> findAllUser();
	/**
	 * @desc 添加一个用户【系统管理员】
	 * @param sysUser
	 * @return 用户总数
	 */
	public int addSysUser(SysUser sysUser);
	/**
	 * @desc 更新某个用户的信息
	 * @param sysUser
	 * @return
	 */
	public boolean updateUser(SysUser sysUser);
	/**
	 * @desc  根id删除某个用户
	 * @param id
	 * @return
	 */
	public boolean deleteUser(String id);
	

}
