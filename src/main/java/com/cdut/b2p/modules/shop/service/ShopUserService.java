package com.cdut.b2p.modules.shop.service;

import java.util.Date;
import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopUser;
import com.cdut.b2p.modules.shop.po.ShopWallet;

public interface ShopUserService {
	
	public void saveUser(ShopUser shopUser);
	
	public void regUser(String username, String password, String nickname, String email);

	/**
	 * @desc 查询上一个月增加的用户数
	 * @author zsb
	 * @return
	 */
	public Integer addUserCountByMonth();
	/**
	 * @desc 查询上一个月内，浏览网站的游客数量【只注册了，为进行商品买卖行为】
	 * @author zsb
	 * @return
	 */
	public Integer addVistorCountByMonth();
	/**
	 * @desc 查询某一时间段内，注册的用户
	 * @author zsb
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<ShopUser> findUserByDate(Date startDate,Date endDate);
	/**
	 * @desc 查询所有的用户
	 * @author zsb
	 * @return
	 */
	public List<ShopUser> findAllUser();
	
	/**
	 * @desc 更新某个用户的信息
	 * @author zsb
	 * @param sysUser
	 * @return
	 */
	public boolean updateUser(ShopUser shopUser);
	/**
	 * @desc 根据用户id，更新密码
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean updatePWD(String id,String pwd);
	/**
	 * @desc 根据id，更新某用户的头像
	 * @param id
	 * @param fileName
	 * @return
	 */
	public boolean updateImage(String id,String fileName);
	/**
	 * @desc  根id删除某个用户
	 * @author zsb
	 * @param id
	 * @return
	 */
	
	
	public boolean deleteUser(String id);

	public void saveWallet(ShopWallet shopWallet);
	
	public ShopUser findUserByUsername(String username);
	
	public ShopUser findUserByEmail(String email);
	
	public ShopUser findUserByNickname(String nickname);
	
	public ShopUser findUserById(String uid);



}
