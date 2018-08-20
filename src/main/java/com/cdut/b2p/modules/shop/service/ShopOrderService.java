package com.cdut.b2p.modules.shop.service;

import com.cdut.b2p.modules.shop.po.ShopGoods;
import com.cdut.b2p.modules.shop.po.ShopOrder;

public interface ShopOrderService {
		//增加订单
		public void saveOrder(ShopOrder shoporder);
		//修改订单信息()
		public void changeOrder(ShopOrder shoporder);
		//删除订单(根据订单id)
		public void deleteOrder(String orderId);
		//查询订单(根据订单id)
		public ShopOrder findOrder(String orderId);
}
