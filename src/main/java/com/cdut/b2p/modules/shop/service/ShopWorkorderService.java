package com.cdut.b2p.modules.shop.service;

import java.util.List;

import com.cdut.b2p.modules.shop.po.ShopWorkorder;

public interface ShopWorkorderService {
	/**
	 * @desc 增加一条投诉信息
	 * @param shopWorkorder
	 * @return
	 */
	public boolean addShopWorkOrder(ShopWorkorder shopWorkorder);
	/**
	 * @desc 根据接受者的id，查询相应的消息
	 * @param uid
	 * @return
	 */
	public List<ShopWorkorder> findWorkOrderByUser(String uid);
	/**
	 * @desc 根据id，删除相应的记录
	 * @param id
	 * @return
	 */
	public boolean delWorkOrderById(String id);
}
