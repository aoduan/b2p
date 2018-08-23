package com.cdut.b2p.modules.shop.service;

import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopAddress;

/**
 * @desc ShopAddressService是有关用户收货地址表的服务接口
 * @author zsb
 *
 */
public interface ShopAddressService {
	/**
	 * @desc 添加新地址
	 * @param shopAddress
	 * @return
	 */
	public boolean addAddress(ShopAddress shopAddress);
	/**
	 * @desc 根据用户id，查询其收获地址表
	 * @param uid
	 * @return
	 */
	public List<ShopAddress> findAddressByUser(String uid);
}
