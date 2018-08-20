package com.cdut.b2p.modules.shop.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdut.b2p.modules.shop.mapper.ShopOrderMapper;
import com.cdut.b2p.modules.shop.po.ShopOrder;
import com.cdut.b2p.modules.shop.po.ShopOrderExample;
import com.cdut.b2p.modules.shop.service.ShopOrderService;

@Service
@Transactional
public class ShopOrderServiceImpl implements ShopOrderService{
	@Autowired
	private ShopOrderMapper shopordermapper;
	//检查
		
	//添加订单
	@Override
	public void saveOrder(ShopOrder shoporder) {
		// TODO Auto-generated method stub
		shopordermapper.insert(shoporder);
		
	}
	//修改订单
	@Override
	public void changeOrder(ShopOrder shoporder) {
		// TODO Auto-generated method stub
		shoporder.setUpdateDate(new Date());
		shopordermapper.insert(shoporder);
	}
	//删除订单
	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		ShopOrderExample soe=new ShopOrderExample();
		soe.or().andIdEqualTo(orderId);
		shopordermapper.deleteByExample(soe);
	}
	//查询订单
	@Override
	public ShopOrder findOrder(String orderId) {
		// TODO Auto-generated method stub
		ShopOrderExample soe=new ShopOrderExample();
		soe.or().andIdEqualTo(orderId);
		return shopordermapper.selectByExample(soe).get(0);
		
	}

}
