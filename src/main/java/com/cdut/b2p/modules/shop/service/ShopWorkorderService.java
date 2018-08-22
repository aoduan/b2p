package com.cdut.b2p.modules.shop.service;

import com.cdut.b2p.modules.shop.po.ShopWorkorder;

public interface ShopWorkorderService {
	/**
	 * @desc 增加一条投诉信息
	 * @param shopWorkorder
	 * @return
	 */
	public boolean addShopWorkOrder(ShopWorkorder shopWorkorder);
}
